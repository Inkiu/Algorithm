def solution(tickets):
    ticket = dict()

    for t in tickets:
        if t[0] not in ticket:
            ticket[t[0]] = list()
        ticket[t[0]].append(t[1])

    for k, v in ticket.items():
        v.sort(reverse=True)

    ans = ['ICN']
    visit = 0

    while visit < len(tickets):
        from_city = ans[-1]
        possibles = ticket[from_city]
        visit += 1
        ans.append(possibles.pop())

    return ans


from collections import defaultdict


ticket = defaultdict(list)
visits = dict()
stack = list()


def visit(city, depth, target_dp):
    stack.append(city)

    if depth == target_dp:
        return 1

    for node in ticket[city]:
        i, c = node
        if not visits[i]:
            visits[i] = True

            if visit(c, depth + 1, target_dp):
                return True

            visits[i] = False

    stack.pop()


def solution_v2(tickets):
    for k, t in enumerate(tickets):
        ticket[t[0]].append((k, t[1]))
        visits[k] = False

    for k, v in ticket.items():
        v.sort(key=lambda x: x[1])

    visit('ICN', 0, len(tickets))
    return stack


# import random
#
# possible = ['ICN', 'A', 'B', 'C', 'D', 'E', 'F']
# t = False
# while not t:
#     target = ['ICN']
#     last = 0
#     for _ in range(random.randint(5, 5)):
#         po = random.randint(0, len(possible)-1)
#         while po == last:
#             po = random.randint(0, len(possible)-1)
#         target.append(possible[po])
#         last = po
#
#     problem = []
#     for i in range(len(target)-1):
#         problem.append([target[i], target[i+1]])
#     try:
#         solution(problem)
#     except IndexError:
#         print(problem)
#         t = True
#     except KeyError:
#         print(problem)
#         t = True


# print(
#     solution(
#         [['ICN', 'b'], ['e', 'f'], ['c', 'd'], ['d', 'e'], ['b', 'c']]
#     )
# )


# print(
#     solution_v2(
#         [['ICN', 'b'], ['e', 'f'], ['c', 'd'], ['d', 'e'], ['b', 'c']]
#     )
# )

# print(solution([['ICN', 'D'], ['D', 'F'], ['F', 'D'], ['D', 'F'], ['F', 'A']]))

print(solution_v2([['ICN', 'D'], ['D', 'F'], ['F', 'D'], ['D', 'F'], ['F', 'A']]))

print(
    solution(
        [['ICN', 'SFO'], ['ICN', 'ATL'], ['SFO', 'ATL'], ['ATL', 'ICN'], ['ATL', 'SFO']]
    )
)

print(
    solution_v2(
        [['ICN', 'SFO'], ['ICN', 'ATL'], ['SFO', 'ATL'], ['ATL', 'ICN'], ['ATL', 'SFO']]
    )
)

