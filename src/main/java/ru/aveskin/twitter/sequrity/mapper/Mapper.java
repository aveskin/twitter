package ru.aveskin.twitter.sequrity.mapper;

public interface Mapper<D, S> {
    D map(S source);
}
