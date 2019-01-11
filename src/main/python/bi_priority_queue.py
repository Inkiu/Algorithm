from collections import deque


def insert_binary_search(q: deque, e):
    if not q:
        q.append(e)
    elif q[-1] < e:
        q.append(e)
    elif q[0] > e:
        q.insert(0, e)
    else:
        st = 0
        en = len(q)
        while abs(st - en) > 1:
            index = (st + en) // 2
            if q[index] > e:
                en = index
            elif q[index] < e:
                st = index
            else:
                st = index
                en = index

        if abs(st - en) == 1:
            q.insert(en, e)
        else:
            q.insert(st, e)


def solution(operations):
    answer = deque()
    for op in operations:
        o = op[0]
        n = int(op[2:])

        if o == 'I':
            insert_binary_search(answer, n)
        elif n == -1:
            if answer: answer.popleft()
        elif n == 1:
            if answer: answer.pop()
        else:
            raise Exception()

    ans = [0, 0]
    if answer:
        ans = [answer[-1], answer[0]]
    return ans