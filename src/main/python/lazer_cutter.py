
def solution(arrangement):
    stack = []
    last = ')'
    ans = 0
    for s in arrangement:
        if last == '(' and s == ')':
            stack.pop()
            ans += len(stack)
        elif s == ')':
            stack.pop()
            ans += 1
        else:
            stack.append(')')
        last = s

    return ans


print(solution('()(((()())(())()))(())'))
