/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pedroaugusto.mycomponents;

import java.util.List;

/**
 *
 * @author Pedro
 */
public interface DataProvider {
    public List<String[]> pesquisa(String query);
    public void showAll();
}
