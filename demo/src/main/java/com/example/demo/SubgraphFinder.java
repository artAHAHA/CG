package com.example.demo;

import java.util.List;

public interface SubgraphFinder {
    void findSubgraphs(List<int[]> current, int index, int M);
    List<List<int[]>> getSubgraphs();
}