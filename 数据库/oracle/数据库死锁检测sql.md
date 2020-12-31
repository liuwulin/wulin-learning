更新或删除某条记录的时候，无法操作，或被锁解决
 
有的时候，让我们在oracle上做update 或者delete的时候，出现上时间无法获得操作结果，
一直处于正在处理之中，这时候要考虑是否oracle本身对该记录进行锁住了。
 
### 1：查看记录是否被锁
```
SELECT a.object_id, a.session_id, b.object_name
FROM v$locked_object a, dba_objects b
WHERE a.object_id = b.object_id
```

### 2：查看被锁记录ID
```
select object_name,machine,s.sid,s.serial#
from v$locked_object l,dba_objects o ,v$session s
where l.object_id = o.object_id and l.session_id=s.sid
```
 
### 3：删除或解锁该记录
```
alter system kill session 'sid,serial#'(例如：alert system kill session '1,1212')
```