package ru.aveskin.twitter.security.mapper;

public interface Mapper<D, S> {
    D map(S source);
}
