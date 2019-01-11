class Node:
    def __init__(self, n):
        self.n = n

    def __cmp__(self, other):
        first = str(self.n) + str(other.n)
        second = str(other.n) + str(self.n)
        return int(first) > int(second)

    def __lt__(self, other):
        return self.__cmp__(other)


def solution(numbers):
    targets = [Node(i) for i in numbers]
    targets.sort()
    targets = [str(node.n) for node in targets]
    return str(int(''.join(targets)))
