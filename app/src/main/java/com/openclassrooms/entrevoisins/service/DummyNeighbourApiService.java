package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();


    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
    }

    /**
     * {@inheritDoc}
     * @param neighbour
     */
    @Override
    public void createNeighbour(Neighbour neighbour) {
        neighbours.add(neighbour);
    }

    @Override
    public Neighbour getNeighbour(long id) {
        for (Neighbour neighbour : neighbours ){
                if (neighbour.getId()==id){
                    return neighbour;
                }
        }
        return null;
    }

    @Override
    public void changeFavourite(Neighbour neighbour) {
        neighbour.setFavourite(!neighbour.isFavourite());    }

    @Override
    public List<Neighbour> getFavouriteNeighbour() {
        List<Neighbour> favourite = new ArrayList<>();
        for (Neighbour neighbour : neighbours ){
            if (neighbour.isFavourite()){
               favourite.add(neighbour);

            }
        }

        return favourite;
    }
}
