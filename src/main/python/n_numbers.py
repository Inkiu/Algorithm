from collections import deque


def solution(n, number):
    paths = [0] * (32000 * n + 1)
    paths[0] = 1
    tmp = 1
    while tmp < len(paths):
        paths[tmp * n] = len(str(tmp))
        tmp = int(str(tmp) + str(tmp))

    q = deque()
    q.append((number, 1))

    while q:
        num, dp = q.popleft()
        if dp > 8:
            return -1

        # plus
        plus = num + n
        if plus < len(paths) and paths[plus]:
            return paths[plus] + dp
        q.append((plus, dp+1))

        # minus
        minus = num - n
        if -1 < minus < len(paths) and paths[minus]:
            return paths[minus] + dp
        q.append((minus, dp+1))

        # mul
        mul = num * n
        for i in range(n):
            if mul+i < len(paths) and paths[mul+i]:
                return paths[mul+i]+dp
            q.append((mul+i, dp+1))

        # divide
        if num % n == 0:
            divide = num // n
            if divide < len(paths) and paths[divide]:
                return paths[divide] + dp
            q.append((divide, dp+1))

    return None


print(solution(5, 25))
print(solution(5, 2))
print(solution(5, 23))
print(solution(5, 83))
# print(solution(5, 31168))