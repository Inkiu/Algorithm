from collections import defaultdict


def solution(genres, plays):
    d = defaultdict(list)
    c = defaultdict(lambda : 0)

    for i in range(len(plays)):
        d[genres[i]].append([i, plays[i]])
        c[genres[i]] += plays[i]

    g_counts = list()
    for k, v in c.items():
        g_counts.append([k, v])
    g_counts.sort(key=lambda x: -x[1])

    ans = list()

    for g in g_counts:
        g, l = g
        lists = d[g]
        lists.sort(key=lambda x: (-x[1], x[0]))
        ans.append(lists[0][0])
        if len(lists) > 1:
            ans.append(lists[1][0])

    return ans


import random

gen_candidates = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h']

while True:
    gens = [gen_candidates[random.randint(0, len(gen_candidates) - 1)] for _ in range(100)]
    play = [random.randint(100, 1000) for _ in range(100)]
    solution(gens, play)