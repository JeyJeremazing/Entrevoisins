package com.openclassrooms.entrevoisins.service;


import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import com.openclassrooms.entrevoisins.model.Neighbour;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.nio.file.attribute.FileAttributeView;
import java.util.List;

@RunWith(JUnit4.class)
public class DummyNeighbourApiServiceTest {

    DummyNeighbourApiService neighbourApiService = new DummyNeighbourApiService();


    @Test
   public void getNeighbours() {
        List<Neighbour> neighbours = this.neighbourApiService.getNeighbours();
        List<Neighbour> dummyNeighbour = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
        assertThat(neighbours, containsInAnyOrder(dummyNeighbour.toArray()));
    }

    @Test
    public void deleteNeighbour() {
        List<Neighbour> neighbours = this.neighbourApiService.getNeighbours();
        Neighbour neighbour = neighbours.get(0);
        int numberNeighboursBefore = neighbours.size();
        neighbourApiService.deleteNeighbour(neighbour);
        int numberNeighboursAfter=neighbours.size();
        assertTrue(numberNeighboursBefore>numberNeighboursAfter);

    }

    @Test
    public void createNeighbour() {
        Neighbour neighbour = new Neighbour(100, "Jérémy", "https://i.pravatar.cc/150?u=a042581f4e29026704k", "Saint-Pierre-du-Mont ; 5km",
                "+33 6 86 57 90 25",  "Bonjour !Je souhaiterais faire de la marche nordique. Pas initiée, je recherche une ou plusieurs personnes susceptibles de m'accompagner !J'aime les jeux de cartes tels la belote et le tarot..");
        neighbourApiService.createNeighbour(neighbour);
        assertTrue(neighbourApiService.getNeighbours().contains(neighbour));

    }

    @Test
    public void getNeighbour() {

      Neighbour dummyNeighbour = DummyNeighbourGenerator.DUMMY_NEIGHBOURS.get(0);
        Neighbour neighbour = neighbourApiService.getNeighbour(dummyNeighbour.getId());
        assertEquals(dummyNeighbour.getId(),neighbour.getId());
        assertEquals(dummyNeighbour.getAvatarUrl(),neighbour.getAvatarUrl());
        assertEquals(dummyNeighbour.getName(),neighbour.getName());
        assertEquals(dummyNeighbour.getPhoneNumber(),neighbour.getPhoneNumber());
    }

    @Test
    public void changeFavourite() {
        Neighbour dummyNeighbour = DummyNeighbourGenerator.DUMMY_NEIGHBOURS.get(0);
        dummyNeighbour.setFavourite(false);
        neighbourApiService.changeFavourite(dummyNeighbour);
        assertTrue(dummyNeighbour.isFavourite());

        dummyNeighbour.setFavourite(true);
        neighbourApiService.changeFavourite(dummyNeighbour);
        assertFalse(dummyNeighbour.isFavourite());
    }

    @Test
    public void getFavouriteNeighbour() {
        List<Neighbour> favouriteNeighbour = neighbourApiService.getFavouriteNeighbour();
        assertTrue(favouriteNeighbour.isEmpty());
        Neighbour dummyNeighbour = DummyNeighbourGenerator.DUMMY_NEIGHBOURS.get(0);
        neighbourApiService.changeFavourite(dummyNeighbour);
        favouriteNeighbour = neighbourApiService.getFavouriteNeighbour();
        assertEquals(favouriteNeighbour.size(),1);
    }
}