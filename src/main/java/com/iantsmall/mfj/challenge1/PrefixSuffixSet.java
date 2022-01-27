package com.iantsmall.mfj.challenge1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Stream;

class PrefixSuffixSet {

    private Collection<String> prefixes = new ArrayList<String>();
    private Collection<String> suffixes = new ArrayList<String>();

    PrefixSuffixSet(){
    }

    public void addPrefix(String word) {
        this.prefixes.add(word);
    }

    public void addSuffix(String word) {
        this.prefixes.add(word);
    }

    /**
     * Converts the
     * @return
     */
    public Stream<WordPair> toWordPairs(){
        var prefixesStream = this.prefixes.stream();
        var suffixesStream = this.suffixes.stream();
        return prefixesStream.flatMap(p -> suffixesStream.map(s ->  new WordPair(p,s)));
    }

}
