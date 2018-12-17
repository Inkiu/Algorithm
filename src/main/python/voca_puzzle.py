def find_target(len_of_target, target, strs, cur_str, depth):
    found_depth = 20001
    for s in strs:
        
        if target[len(cur_str)] != s[0]:
            continue
        
        tmp = cur_str + s
        
        if target == tmp:
            return depth
        
        if len_of_target > len(tmp):
            dp = find_target(len_of_target, target, strs, cur_str + s, depth + 1)
            found_depth = min(found_depth, dp)
        else:
            pass
        
    return found_depth

def solution(strs, t):
    counts = [20001] * len(t)
    
    for i, c in enumerate(t):
        for s in strs:
            if s[-1] == c and t[i - len(s) + 1: i + 1] == s:
                if i - len(s) == -1: #시작지점
                    counts[i] = 1
                else:
                    counts[i] = min(counts[i - len(s)] + 1, counts[i])
                
    return counts[-1] if counts[-1] < 20001 else -1

    #found = find_target(len(t), t, strs, "", 1)
    #return found if found != 20001 else -1