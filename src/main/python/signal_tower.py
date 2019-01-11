def brute_force(heights):
    l = len(heights)
    answer = [0] * l

    for i in range(l):
        h = heights[l- i - 1]
        for j in range(l):
            if l- i - 1 < l - j - 1:
                continue
            t =  heights[l - j - 1]
            if t > h:
                answer[l- i - 1] = l - j
                break

    return answer


def solution(heights):
    length = len(heights)
    stack = []

    ans = [0] * length

    for i, h in enumerate(heights):
        while stack:
            if stack[-1][1] <= h:  # 같으면 제거
                stack.pop(-1)
            else:
                break
        if stack:
            ans[i] = stack[-1][0]

        stack.append([i, h])

    return ans
