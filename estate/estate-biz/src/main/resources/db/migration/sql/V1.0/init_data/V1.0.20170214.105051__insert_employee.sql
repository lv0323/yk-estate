INSERT INTO t_company (name, license, address, introduction, secret_key, start_date, end_date)
VALUES ('盈科地产', '', '北京', '简介', 'bjykdc', now(), NULL);


INSERT INTO t_position (company_id, name, note, type)
VALUES (1, '总经理', '', 'ADMIN'),
  (1, '运营总监', '', 'OPERATION'),
  (1, '店经理', '', 'DEPT_M'),
  (1, '置业顾问', '', 'BUSINESS'),
  (1, '财务', '', 'FINANCIAL');

INSERT INTO t_department (parent_id, company_id, NAME, telephone, address, city_id, district_id, sub_district_id)
VALUES (0, 1, '一号分店', '400-818-1116', '北京', 1, 14, 175);


INSERT INTO t_employee (company_id, department_id, position_id, is_boss, mobile, PASSWORD, salt, NAME, gender, idcard_number, wechat, status, avatar_id)
VALUES (1, 1, 1, TRUE, '13165498765', 'd4d9f64521ea1c4d5ed9bdefb29c757a', '8e11cd6186cc4dde82097e036dd04ba1', '张三', 'M',
           '360326594563256111', NULL, 'WORKING', NULL);