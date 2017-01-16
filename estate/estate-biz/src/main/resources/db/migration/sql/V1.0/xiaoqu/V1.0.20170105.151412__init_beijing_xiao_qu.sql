INSERT INTO t_xiao_qu (community_id, ranking) SELECT DISTINCT
                                                id,
                                                0
                                              FROM t_community
                                              ORDER BY id;

