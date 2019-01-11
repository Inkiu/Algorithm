from collections import defaultdict


def solution(K, C, D):
    ans = 0

    clean_d = defaultdict(lambda : 0)
    for c in C:
        clean_d[c] += 1
    for k in clean_d.keys():
        fair = clean_d[k]
        ans += fair // 2
        clean_d[k] = fair % 2

    dirty_d = defaultdict(lambda : 0)
    for d in D:
        dirty_d[d] += 1

    for k, v in clean_d.items():
        for i in range(v):
            if K == 0:
                break
            dirty = dirty_d[k]
            if dirty:
                K -= 1
                ans += 1
                dirty_d[k] -= 1

    for k, v in dirty_d.items():
        while v > 1:
            if K < 2:
                break
            ans += 1
            v -= 2
            K -= 2

    return ans


import random
# solution(1, [1, 2, 3], [4, 5, 6, 1])

while True:
    random_k = random.randint(1, 10)
    random_c = [random.randint(1, 10) for _ in range(random.randint(1, 10))]
    random_d = [random.randint(1, 10) for _ in range(random.randint(1, 10))]
    s1 = solution(random_k, random_c, random_d)
    s2 = re_solution(random_k, random_c, random_d)
    if s1 != s2:
        print(s1, s2, random_k, sorted(random_c), sorted(random_d))
        break
