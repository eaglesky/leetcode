 ListNode *reverseBetween0(ListNode *head, int m, int n) {
        ListNode* dummy = new ListNode(-1);
        dummy->next = head;
        
        head = dummy;
        for (int i = 0; i < m-1; ++i)
            head = head->next;
            
        ListNode* prev = head->next;
        ListNode* cur = prev->next;
        ListNode* post = cur ? cur->next : NULL;
        int id = m+1;
        while (id <= n) {
            cur->next = prev;
            prev = cur;
            cur = post;
            post = post ? post->next : NULL;
            id++;
        }
       
        head->next->next = cur;
        head->next = prev;
    
        return dummy->next;
    }
    
    ListNode *reverseBetween(ListNode *head, int m, int n) {
        ListNode* dummy = new ListNode(-1);
        dummy->next = head;
        
        head = dummy;
        for (int i = 0; i < m-1; ++i)
        {
            head = head->next;
        }
        
        ListNode* prev = head->next;
        ListNode* cur = prev->next;
        for (int i = m; i < n; ++i)
        {
            prev->next = cur->next;
            cur->next = head->next;
            head->next = cur;
            cur = prev->next;
        }
        
        return dummy->next;
    }