# MaxPairsCombination
Maximum Pairs Combination 

This project creates the maximum combination of Letter Pairs, given a N amount of letters and a K value (between 0 and 1) of possible pairs. 
For example, if we have 4 letters and possible K value is 0.5 then the number of couples that will be created is:  (((n * n) / 2) * k). 
For this example will be 4 pairs.

So, if we have the letters: [A, B, C, D]  and the pairs: [BC, BA, DB, DC] then the maximum combinations should be: [BCD, AB, BD].

To find the combinations given the letters and the pairsthe following formulas should be valid:
1. The length of the joined pairs must be N*(N-1), where N is the length of the letters joined.
Eg. The length of BCD is 3, so the length of their pairs BCDBDC must be 3*(3-1) = 6, which is true.
2. Every letter must exist N-1 times inside this pairs. So B, C, D must exist each on 2 times, which is also true.

