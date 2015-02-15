package ru.croacker.lbutil.database.convertor;

/**
 *
 */
public interface Convertor<T, M> {

  public M toMetadata(T table);

  public T toTable(M metadata);

}
