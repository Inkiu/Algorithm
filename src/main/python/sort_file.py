
class Node:

    def __init__(self, word: str, order):
        self.order = order
        self.first = ''
        self.second = ''
        self.origin = word

        state = 0
        for c in word.lower():
            if state == 0:
                if not c.isnumeric():
                    self.first += c
                else:
                    state = 1
                    if c.isnumeric():
                        self.second += c
            elif state == 1:
                if c.isnumeric():
                    self.second += c
                else:
                    break
        self.second = int(self.second)


def solution(files):

    ns = [Node(w, i) for i, w in enumerate(files)]

    ns = sorted(ns, key=lambda n: (n.first, n.second, n.order))

    return [n.origin for n in ns]


solution(["img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG"])
solution(["F-5 Freedom Fighter", "B-50 Superfortress", "A-10 Thunderbolt II", "F-14 Tomcat"])