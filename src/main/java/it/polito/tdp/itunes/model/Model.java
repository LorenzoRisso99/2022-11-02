package it.polito.tdp.itunes.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.itunes.db.ItunesDAO;

public class Model {
	
	private ItunesDAO dao;
	
	private Graph<Track, DefaultEdge> grafo;
	
	List<Track> vertici;
	
	List<Adiacenze> adiac;
	
	Map<Integer, Track> idMap;
	
	
	public Model() {
		
		dao = new ItunesDAO();
		
		vertici = new LinkedList<>();
		
		adiac = new ArrayList<>();
		
		idMap = new HashMap<>();
		
		
	}
	
	public void creaGrafo(String genere, int min, int max) {
		
		grafo = new SimpleGraph<>(DefaultEdge.class);
		
		//Vertici
		
		vertici = this.dao.getVertici(genere, min, max, idMap);
		
		Graphs.addAllVertices(this.grafo, vertici);
		
		System.out.println("n vertici " + grafo.vertexSet().size());
		
		//Archi
		
		adiac = this.dao.getAdiacenze(genere, min, max, idMap);
		
		for(Adiacenze a1 : adiac) {
			for(Adiacenze a2 : adiac) {
				if(a1.getNum()==a2.getNum() && a1.getName() != a2.getName()) {
					Graphs.addEdgeWithVertices(this.grafo, a1.getName(), a2.getName());
				}
			}
		}
		
	}
	
	
	public int nVertici() {
		return this.grafo.vertexSet().size();
	}
	
	public int nArchi() {
		return this.grafo.edgeSet().size();
	}
	
	public List<String> getGeneri() {
		List<String> generi = new LinkedList<>(dao.getAllGenres());
		Collections.sort(generi);
		return generi;
	}

	

	
}
