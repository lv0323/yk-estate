ALTER TABLE t_district_rel
  ADD COLUMN is_primary VARCHAR(1) DEFAULT 'N';


UPDATE t_district_rel
SET is_primary = 'Y'
WHERE id IN (
  SELECT min_id
  FROM (
         SELECT
           min(id) AS min_id,
           sub_district_id
         FROM t_district_rel
         GROUP BY sub_district_id)
    AS a
);