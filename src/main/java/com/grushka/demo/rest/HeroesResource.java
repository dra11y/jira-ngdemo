package com.grushka.demo.rest;

import com.atlassian.activeobjects.external.ActiveObjects;
import com.atlassian.plugin.spring.scanner.annotation.component.Scanned;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import com.atlassian.plugins.rest.common.security.AnonymousAllowed;
import com.grushka.demo.entity.Hero;
import net.java.ao.DBParam;
import net.java.ao.Query;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A resource of heroes.
 */
@Path("/heroes")
@Scanned
public class HeroesResource {
    @ComponentImport
    private final ActiveObjects ao;

    public HeroesResource(ActiveObjects ao) {
        this.ao = ao;
    }

    @GET
    @AnonymousAllowed
    @Produces({MediaType.APPLICATION_JSON})
    // return a list of heroes
    public List<HeroModel> getHeroes(@QueryParam("name") String name)
    {
        if (name != null && name.trim().length() > 0) {
            // return filtered list
            return Arrays.stream(
                ao.find(
                    Hero.class,
                    Query.select().where(
                        "NAME LIKE ?", name.trim() + "%"
                    )
                )
                )
                .map(HeroModel::new)
                .collect(Collectors.toList());
        } else {
            // return all
            return Arrays.stream(ao.find(Hero.class))
                .map(HeroModel::new)
                .collect(Collectors.toList());
        }
    }

    @GET
    @Path("/{id}")
    @AnonymousAllowed
    @Produces({MediaType.APPLICATION_JSON})
    // return a hero instance
    public HeroModel getHero(@PathParam("id") Integer id)
    {
        Hero hero = ao.get(Hero.class, id);
        if (hero == null) {
            throw new WebApplicationException(404);
        }
        return new HeroModel(hero);
    }

    @PUT
    @Path("/{id}")
    @AnonymousAllowed
    @Produces({MediaType.APPLICATION_JSON})
    // update a hero
    public HeroModel updateHero(
        @PathParam("id") Integer id,
        HeroModel heroModel
    )
    {
        Hero hero = ao.get(Hero.class, id);
        if (hero == null) {
            throw new WebApplicationException(404);
        }
        hero.setName(heroModel.name);
        hero.save();
        return new HeroModel(hero);
    }

    @POST
    @AnonymousAllowed
    @Produces({MediaType.APPLICATION_JSON})
    // create a new hero
    public HeroModel createHero(
        HeroModel heroModel
    )
    {
        Hero hero = ao.create(
            Hero.class,
            new DBParam("NAME", heroModel.name)
        );
        return new HeroModel(hero);
    }

    @DELETE
    @Path("/{id}")
    @AnonymousAllowed
    @Produces({MediaType.APPLICATION_JSON})
    // delete a hero
    public Response deleteHero(@PathParam("id") Integer id) {
        Hero hero = ao.get(Hero.class, id);
        if (hero == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        ao.delete(hero);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}