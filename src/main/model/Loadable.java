package model;

import exceptions.EntryInsufficientException;
import exceptions.MazeRecordInvalidException;

public interface Loadable {

    void load(String str) throws EntryInsufficientException, MazeRecordInvalidException;
}
