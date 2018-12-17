import random


def solution(N):
    n = N
    start_count = False
    max_count = 0
    epoch_count = 0

    while n > 0:
        start_count = start_count or n % 2 == 1

        if start_count and n % 2 == 0:
            epoch_count += 1
        else:
            epoch_count = 0

        max_count = max(max_count, epoch_count)
        n //= 2

    return max_count


test_case = random.randrange(1, 2147483647)
print(test_case, bin(test_case))
print(solution(test_case))






