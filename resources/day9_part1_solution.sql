create table t1 (id int NOT NULL AUTO_INCREMENT, value int,
                PRIMARY KEY (id));
INSERT INTO t1 (value)  values(35);
INSERT INTO t1 (value)  values(20);
INSERT INTO t1 (value)  values(15);
INSERT INTO t1 (value)  values(25);
INSERT INTO t1 (value)  values(47);
INSERT INTO t1 (value)  values(40);
INSERT INTO t1 (value)  values(62);
INSERT INTO t1 (value)  values(55);
INSERT INTO t1 (value)  values(65);
INSERT INTO t1 (value)  values(95);
INSERT INTO t1 (value)  values(102);
INSERT INTO t1 (value)  values(117);
INSERT INTO t1 (value)  values(150);
INSERT INTO t1 (value)  values(182);
INSERT INTO t1 (value)  values(127);
INSERT INTO t1 (value)  values(219);
INSERT INTO t1 (value)  values(299);
INSERT INTO t1 (value)  values(277);
INSERT INTO t1 (value)  values(309);
INSERT INTO t1 (value)  values(576);

select id, value from t1 main
left join (select first.id id1, second.id id2, first.value+second.value sum from
                 t1 first join t1 second on first.value <> second.value) sums
on main.value = sums.sum and id1 >= main.id-5 and id1 <= main.id-1 and id2 >= main.id-5 and id2 <= main.id-1
where
main.id>5
and sum is null;