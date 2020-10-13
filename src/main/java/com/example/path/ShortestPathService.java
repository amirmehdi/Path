package com.example.path;

import com.example.path.model.FlightDetail;
import com.example.path.model.RoadDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShortestPathService implements CommandLineRunner {

    private final PathDetailHolder pathDetailHolder;
    private ArrayList<String> vectorOfGraph = new ArrayList<>();
    private ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

    public ShortestPathService(PathDetailHolder pathDetailHolder) {
        this.pathDetailHolder = pathDetailHolder;
    }

    // function to print the shortest distance and path
    // between source vertex and destination vertex
    public List<String> printShortestDistance(
            String source, String destination) {
        // predecessor[i] array stores predecessor of
        // i and distance array stores distance of i
        // from s

        int s = vectorOfGraph.indexOf(source);
        int dest = vectorOfGraph.indexOf(destination);
        int v = vectorOfGraph.size();
        int pred[] = new int[v];
        int dist[] = new int[v];

        if (BFS(s, dest, v, pred, dist) == false) {
            System.out.println("Given source and destination" +
                    "are not connected");
            return Collections.emptyList();
        }

        // LinkedList to store path
        LinkedList<Integer> path = new LinkedList<Integer>();
        int crawl = dest;
        path.add(crawl);
        while (pred[crawl] != -1) {
            path.add(pred[crawl]);
            crawl = pred[crawl];
        }

        List<String> vectors = new ArrayList<>();
        for (int i = path.size() - 1; i >= 0; i--) {
            vectors.add(vectorOfGraph.get(path.get(i)));
        }
        return vectors;
    }

    // a modified version of BFS that stores predecessor
    // of each vertex in array pred
    // and its distance from source in array dist
    private boolean BFS(int src, int dest, int v, int pred[], int dist[]) {
        // a queue to maintain queue of vertices whose
        // adjacency list is to be scanned as per normal
        // BFS algorithm using LinkedList of Integer type
        LinkedList<Integer> queue = new LinkedList<Integer>();

        // boolean array visited[] which stores the
        // information whether ith vertex is reached
        // at least once in the Breadth first search
        boolean visited[] = new boolean[v];

        // initially all vertices are unvisited
        // so v[i] for all i is false
        // and as no path is yet constructed
        // dist[i] for all i set to infinity
        for (int i = 0; i < v; i++) {
            visited[i] = false;
            dist[i] = Integer.MAX_VALUE;
            pred[i] = -1;
        }

        // now source is first to be visited and
        // distance from source to itself should be 0
        visited[src] = true;
        dist[src] = 0;
        queue.add(src);

        // bfs Algorithm
        while (!queue.isEmpty()) {
            int u = queue.remove();
            for (int i = 0; i < adj.get(u).size(); i++) {
                if (visited[adj.get(u).get(i)] == false) {
                    visited[adj.get(u).get(i)] = true;
                    dist[adj.get(u).get(i)] = dist[u] + 1;
                    pred[adj.get(u).get(i)] = u;
                    queue.add(adj.get(u).get(i));

                    // stopping condition (when we find
                    // our destination)
                    if (adj.get(u).get(i) == dest)
                        return true;
                }
            }
        }
        return false;
    }

    public void preProcess(List<RoadDetail> roadDetails, List<FlightDetail> flightDetails) {
        roadDetails.stream().map(RoadDetail::getSource).distinct().forEach(s -> vectorOfGraph.add(s));
        roadDetails.stream().map(RoadDetail::getDest).distinct().forEach(s -> vectorOfGraph.add(s));
        flightDetails.stream().map(FlightDetail::getSource).distinct().forEach(s -> vectorOfGraph.add(s));
        flightDetails.stream().map(FlightDetail::getDest).distinct().forEach(s -> vectorOfGraph.add(s));

        vectorOfGraph = new ArrayList<>(vectorOfGraph.stream().distinct().collect(Collectors.toList()));

        for (int i = 0; i < vectorOfGraph.size(); i++) {
            adj.add(new ArrayList<>());
        }
        roadDetails.forEach(roadDetail -> {
            adj.get(vectorOfGraph.indexOf(roadDetail.getSource()))
                    .add(vectorOfGraph.indexOf(roadDetail.getDest()));
            adj.get(vectorOfGraph.indexOf(roadDetail.getDest())).add(vectorOfGraph.indexOf(roadDetail.getSource()));
        });
        flightDetails.forEach(roadDetail -> {
            adj.get(vectorOfGraph.indexOf(roadDetail.getSource()))
                    .add(vectorOfGraph.indexOf(roadDetail.getDest()));
            adj.get(vectorOfGraph.indexOf(roadDetail.getDest())).add(vectorOfGraph.indexOf(roadDetail.getSource()));
        });
    }

    @Override
    public void run(String... args) throws Exception {
        preProcess(pathDetailHolder.getRoadDetails(), pathDetailHolder.getFlightDetails());
    }
}
