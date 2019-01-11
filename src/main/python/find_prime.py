from collections import defaultdict
from itertools import permutations

def make_primes(max_value):
    a = [False, False] + [True]*(max_value-1)
    for i in range(2, max_value+1):
        if a[i]:
            for j in range(2*i, max_value+1, i):
                a[j] = False
    return a


def solution(numbers):
    answer = 0
    max_value = int(''.join(sorted([s for s in numbers], reverse=True)))
    primes = make_primes(max_value)

    permutation = list()
    for i in range(len(numbers)):
        permutation.extend(list(map(''.join, permutations(numbers, i + 1))))
    permutation = [int(s) for s in permutation]
    permutation = set(permutation)

    for i in permutation:
        if primes[i]:
            answer += 1

    return answer


print(solution('17'))
print(solution('011'))