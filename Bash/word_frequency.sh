#!/bin/bash

# My first solution
declare -A word_counts
while read -r -a line_array; do
    for word in ${line_array[@]}; do
        word_counts[$word]=$((word_counts[$word]+1))
    done
done < $1

for i in "${!word_counts[@]}"; do
    echo $i ${word_counts[$i]}
done | sort -rn -k2

# Best solution is as follows:
# cat words.txt | tr -s ' ' '\n' | sort | uniq -c | sort -rn -k1 | awk '{print $2,$1}'
