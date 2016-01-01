package com.aaomidi.sortingtelegram.sortalgorithms;

import com.aaomidi.sortingtelegram.SortingTelegram;
import com.aaomidi.sortingtelegram.model.BinarySearchTree;
import com.aaomidi.sortingtelegram.model.Sortable;

/**
 * Created by amir on 2016-01-01.
 */
public class BinarySearchTreeSort extends Sortable {
    public BinarySearchTreeSort(String algorithmName, SortingTelegram instance) {
        super(algorithmName, instance);
    }

    @Override
    public void innerSort(String[] data, int len) {
        BinarySearchTree<String> bst = new BinarySearchTree<>();
        for (String s : data) {
            bst.insert(s);
        }
        int i = 0;
        for (BinarySearchTree.Node n : bst.inorder()) {
            data[i++] = (String) n.getData();
        }
    }
}
