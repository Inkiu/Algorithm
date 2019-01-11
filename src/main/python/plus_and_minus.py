from collections import deque


def solution(numbers, target):
    counts = 0

    q = deque()
    t_dp = len(numbers)-1
    q.append((0,  numbers[0], 0))
    q.append((0, -numbers[0], 0))

    while q:
        acc, op, dp = q.popleft()
        if dp+1 < len(numbers):
            q.append((acc + op,  numbers[dp+1], dp+1))
            q.append((acc + op, -numbers[dp+1], dp+1))
        if dp == t_dp and acc + op == target:
            counts += 1

    return counts


print(solution([1, 1, 1, 1, 1], 3))
print(solution([3, 4, 7, 5], 13))
