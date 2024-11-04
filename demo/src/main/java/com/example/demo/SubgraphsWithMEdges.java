package com.example.demo;

import java.util.*;

public class SubgraphsWithMEdges implements SubgraphFinder {
    private List<int[]> edges;
    private List<List<int[]>> result;

    public SubgraphsWithMEdges(List<int[]> edges) {
        this.edges = edges;
        this.result = new ArrayList<>();
    }

    @Override
    public void findSubgraphs(List<int[]> current, int index, int M) {
        if (current.size() == M) {
            result.add(new ArrayList<>(current));
            return;
        }

        if (index >= edges.size()) {
            return;
        }

        current.add(edges.get(index));
        findSubgraphs(current, index + 1, M);
        current.remove(current.size() - 1);

        findSubgraphs(current, index + 1, M);
    }

    @Override
    public List<List<int[]>> getSubgraphs() {
        return result;
    }
}
