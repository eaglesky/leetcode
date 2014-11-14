//Two-stacks implementation
class MinStack0 {
public:
    void push(int x) {
        if (s.empty() || (x <= mins.top())) {
            mins.push(x);
        } 
        
        s.push(x);
    }

    void pop() {
        if (s.top() == mins.top())
            mins.pop();
            
        s.pop();
    }

    int top() {
        return s.top();
    }

    int getMin() {
        if (mins.empty())
            return INT_MIN;
        else
            return mins.top();
    }

private:
    stack<int> s;
    stack<int> mins;
};

//One-stack solution, not passed, why?
class MinStack {
public:
    void push(int x) {
        if (diffs.empty()) {
            diffs.push(0);
            minVal = x;
        } else {
            diffs.push((long)x - minVal);
            minVal = min((long)x, minVal);
        }
        
    }

    void pop() {
       
        long curDiff = diffs.top();
        diffs.pop();
        
        if (curDiff <= 0)
            minVal = minVal - curDiff;
    
    }

    int top() {
        return (diffs.top() <= 0) ? (int)minVal : (int)(minVal + diffs.top());
    }

    int getMin() {
        return minVal;
    }

private:
    stack<long> diffs;
    long minVal;
};
