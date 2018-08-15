import sys
import heapq


def parse_line():
    return [int(num) for num in sys.stdin.readline().split()]


class Node:
    def __init__(self, count):
        self.count = count
        self.depth = 0
        self.children = []

    def __lt__(self, other):
        return self.count < other.count


def short_solution(texts, arity, counts):
    queue = []
    for t in range(texts):
        heapq.heappush(queue, Node(counts[t]))

    first_group = (len(queue) - arity) % (arity - 1) + 1

    while len(queue) > 1:
        mom = Node(0)
        for _ in range(arity if first_group == 1 else first_group):
            if queue:
                first_group = 1
                child = heapq.heappop(queue)
                mom.count += child.count
                mom.children.append(child)
        heapq.heappush(queue, mom)

    ret = 0
    while queue:
        n = queue.pop()
        if n.children:
            for child in n.children:
                child.depth = n.depth + 1
                queue.append(child)
        else:
            ret += n.count * n.depth

    return ret


cases = parse_line()[0]
for i in range(cases):
    text, ary = parse_line()
    print(short_solution(text, ary, parse_line()))