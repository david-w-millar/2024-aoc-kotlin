#!/usr/bin/env python
# -*- coding: utf-8 -*-
#
# Modified from http://cadlag.org/posts/de-bruijn-sequences.html
# See Also:
#   - https://archive.bridgesmathart.org/2018/bridges2018-447.pdf
#   - https://en.wikipedia.org/wiki/De_Bruijn_sequence
#
# I figure that this might be useful for part 1 because we only
# have 2 operations.
#
# We can enumerate them in a smaller space, and in less time
# by reading them from a file or embedding them in the code
#

def debruijn(k: int) -> list[int]:
    """ Construct a binary de Bruijn sequence with window size k. """
    n = 2**k
    seq = [0]*k
    cur = 0
    seen = {}
    while True:
        seen[cur] = True
        next = (cur << 1) % n
        if next+1 not in seen:
            seq.append(1)
            cur = next+1
        elif next not in seen:
            seq.append(0)
            cur = next
        else:
            break
    return seq[:n]

def main():
    for i in range(1,15):
        nstring = "".join(map(str, debruijn(i)))
        print(f"{i}: {nstring}")

if __name__ == '__main__':
    main()
