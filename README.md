# Letter Box

This is a WIP project. At this time the initial "white board" coding is completed to the point of successfully compiling, but has not yet been tested.
See the [MFJ Challenge 1 Project](https://github.com/users/iantsmall/projects/5) for progress on final completion of this project.

## Requirements and assumptions

NYT has a game called "Letter Boxed". 
It's a square with three (ASCII) letters on each side, and you try to use up all the letters by
making a series of words.

Rules from original game:

Create words by connecting the letters on the sides with a line with straight segments.
The line must travel from one side to a _different side_.
Words must be at least 3 letters long.
Letters can be reused

**Assumptions**

1. Code should be "native" with minimal non-language features (Demonstrating the concept rather than the power of external libraries)
2. Library exceptions are: Junit and Mockito for testing, and Lombok for improved readability (all code initially written w/o Lombok to demonstrate capability)
3. Algorithm should be case-insensitive
4. By "letters" the rules literally mean A-Z
5. Non-matching words can (and should) be ignored entirely once they are detected
6. Use only base language syntax in the implementation ( I will discuss expanded options in "further optimizations")
7. "words" files are of a "reasonable size", i.e. of a size which could be loaded fully into memory without performance issues

### Notes on requirements

Given that letters can be reused, therefore we can ignore the individual letters and traverse the "sides".

With only four sides, a given valid word COULD be represented as a 2 bit value, 
with each bit combination representing a side. This is a possible optimization route to keep in mind. Note that the word
must still end with the same _letter_ as the next word begins with, so this may not be useful.

We really care about 3 properties of a word: 

- Is it a valid match for the given square
- What letter it begins with
- What letter it ends with

## Examples 
Given the square "RME,WCL,KGT,IPA" and a word file containing:

```text
BANANA
RPKLW
RP
.DL
WLR
WPG
```

The output should be (not necessarily in the same line order):

> RPKLW,WLR
>
> RPKLW,WPG
>
> WLR,RPKLW

## Brute force solution

// note that "valid value" is a word at least 3 letters long, with a letter on each side, and no repeating letters.
* create a function isValid(square, word)
  * if word == null or square is not an array of size 4, return false;
  * lastSide = -1
  * for each(letter in word):
    * side = the index of the side the letter is in, -1 if not present
    * if( side == -1 || side == lastSide) return false
    * lastSide = side
  * return true // successfully navigated the square to the end of the word
* create an array wordPairs
* square = squareString.split(',') // split into an array on ','
* For each( prefix in file.lines ):
  * For each( suffix in file.lines ):
    * If (isValid(square, prefix) && isValid(square, prefix) && prefix[0] == suffix[suffix.length-1]):
      * wordPairs.push({prefix, suffix})
* return wordPairs
      
### Big O of Brute Force

Where n is lines in the file:

- Runtime: `O(n^2)` Each line is checked against each line, including itself.
- Memory: `O(n^2)` Each line may be valid and match with each other line, including itself, growing wordPair to size n^2 

## Optimizations

- *Reduce expense of repeated file reads:* Use a 2d array to track with one slot for each letter. 
  - Store the valid words in an array of buckets for the words' starting letter.
the slots based on their first letter. We can both iterate the array for each prefix, and locate 
- *Transfer/storage savings:* use 2 arrays, one for startsWith, and one for endsWith. Instead of returning an array of 
word pairs, instead return a prefixSuffixSet array. These could be "unpacked" later, perhaps in a client side
function, and would represent a dramatic savings in memory. Where p is the number of prefixes, and s is the number of 
suffixes,  then this is an improvement from `O(p*s)` to `O(p+s)`. Expressed instead with n as each word, then `O(n^2)` 
to `O(n)`.
   - This does not follow the letter of the challenge, so I will encapsulate this, and unpack in a wrapper function
   - This does reduce runtime to `O(n)`, but it does so by skipping the final word pair creation.
- *Language optimization:* by processing in file lines asynchronously we can speed up creation of startsWith and 
endsWith by processing lines simultaneously
- *Reduce redundant memory:* startsWith and endsWith are redundant with prefixSuffixSets. 
  - Build prefixSuffixSets directly during processing
- Consider using streams rather than arrays for greater flexibility and language native parallel behavior (presuming java)


### Optimized Algorithm

* create a function isValid(square, word): boolean
  * // note that "valid value" is a word at least 3 letters long, with a letter on each side, and no repeating sides.
  * if word == null or square is not an array of size 4, return false;
  * lastSide = -1
  * for each(letter in word):
    * side = the index of the side the letter is in, -1 if not present
    * if( side == -1 || side == lastSide) return false
    * lastSide = side
  * return true // successfully navigated the square to the end of the word
* define class PrefixSuffixSet
  * define property prefixes = []
  * define property suffixes = []
  * define method toWordPairs(){ return this.prefixes.flatmap(p -> this.suffixes.map(s -> [p, s] ) )
* create a function toWordPairs(filePath, squareString): PrefixSuffixSet[]
  * square = squareString.split(',') // split into an array on ','
  * prefixSuffixSets = Array initialized with 27 new PrefixSuffixSets
  * For each( word in file.lines ): // use a stream
    * If ( isValid(square, word) ):
        * startsWith = word[0]
        * endsWith = word[word.length-1]
        * prefixSuffixSets[endsWith].prefixes.push(word)
        * prefixSuffixSets[startsWith].suffixes.push(word)
  * return prefixSuffixSets;
* return toWordPairs(filePathArg, squareStringArg).flatmap( x -> x.toWordPairs);

## Possible Improvements

### External Libs and Tools

External libs and tools can clean up and enhance the code

### Larger Scale Optimizations

At scale, other options could include:

- Storing the words in a database, and using db native features such as partitions and indexes to rapidly build paginated output
- Building off of data storage centralization in a DB, processing could occur a cloud with many nodes processing at once
- Use a sort of CQRS to set up various steps of the process, and split distribute the steps over time asynchronously
- Instead of a Java implementation a lighter implementation like Node which could run on an AWS lambda could create a massively parallel system.
