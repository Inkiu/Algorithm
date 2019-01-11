def reverse(c):
    if c == ')':
        return '('
    elif c == ']':
        return '['
    elif c == '}':
        return '{'
    return ''


def solution(S):
    st = []

    for s in S:
        c = st[-1] if len(st) else 'empty'
        if c == 'empty':
            st.append(s)
        elif reverse(s) and c == reverse(s):
            st.pop()
        else:
            st.append(s)

    return 0 if len(st) else 1

