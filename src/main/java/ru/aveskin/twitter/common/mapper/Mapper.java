package ru.aveskin.twitter.common.mapper;

public interface Mapper<D, S> {
    D map(S source);
}
