-- �޴�ǥ
INSERT INTO Menu (food_name, food_price, food_description, food_category) 
VALUES ('��', 500, '�ø������� �߻�ͼҽ��� �Բ� �����Ǵ� ��', 'Appetizer');

INSERT INTO Menu (food_name, food_price, food_description, food_category) 
VALUES ('����ġ ��Ͼ� ����L', 10000, '����� ���� ������ ���� ���� ġ� �ø� �������� ���� ���� 500g �Դϴ�.', 'Appetizer');

INSERT INTO Menu (food_name, food_price, food_description, food_category) 
VALUES ('����ġ ��Ͼ� ����S', 5000, '����� ���� ������ ���� ���� ġ� �ø� �������� ���� ���� 150g �Դϴ�.', 'Appetizer');

INSERT INTO Menu (food_name, food_price, food_description, food_category) 
VALUES ('�׸� ������', 13000, '�ż��� ��ä�� ũ����, ġ� �巹������ ���� �� �׸� �������Դϴ�.', 'Salad');

INSERT INTO Menu (food_name, food_price, food_description, food_category) 
VALUES ('���� ������', 14000, '�ż��� ����ȸ ��ä�� �巹�̰� �Բ� ����帮�� �ǰ��� ���� �������Դϴ�', 'Salad');

INSERT INTO Menu (food_name, food_price, food_description, food_category) 
VALUES ('���� ������ũ', 25000, '�ż��� ��� �׸��� ���� ���� �ҽ��� �Բ� ����帮�� �׸��� ���� ������ũ�Դϴ�.', 'Seafood Dish');

INSERT INTO Menu (food_name, food_price, food_description, food_category) 
VALUES ('�Ƚ� ������ũ', 25000, '�ε巴�� �ż��� �Ұ�� ������ũ�Դϴ�.', 'Meat Dish');

INSERT INTO Menu (food_name, food_price, food_description, food_category) 
VALUES ('�丶�� �Ƚ� �Ľ�Ÿ', 13000, '�ż��� �丶��� �ٻ��� �Ұ�� �Ƚ��� �־� ���� �丶�� �Ұ�� �Ƚ� �Ľ�Ÿ�Դϴ�.', 'Pasta');

INSERT INTO Menu (food_name, food_price, food_description, food_category) 
VALUES ('ũ�� �ҽ� ���� �Ľ�Ÿ', 13000, 'ũ�� �ҽ��� ���� �� �ż��� �Ľ�Ÿ�Դϴ�.', 'Pasta');

INSERT INTO Menu (food_name, food_price, food_description, food_category) 
VALUES ('Ƽ��̼�', 8000, '�ε巯�� ����ī���� ġ��� Ŀ�� ���� ��췯�� ��Ż���� ��ǥ ����Ʈ, Ƽ��̼��Դϴ�.', 'Dessert');

-- ���ǥ
INSERT INTO Ingredient (ingredient_name, ingredient_unit)
VALUES ('iƼ��̼�', '��');

INSERT INTO Ingredient (ingredient_name, ingredient_unit)
VALUES ('iũ�� �기', '��');

INSERT INTO Ingredient (ingredient_name, ingredient_unit)
VALUES ('i�巹��', 'g');

INSERT INTO Ingredient (ingredient_name, ingredient_unit)
VALUES ('i��', 'g');

INSERT INTO Ingredient (ingredient_name, ingredient_unit)
VALUES ('i�Ľ�Ÿ', 'g');

INSERT INTO Ingredient (ingredient_name, ingredient_unit)
VALUES ('i���� ������ũ �ҽ�', 'g');

INSERT INTO Ingredient (ingredient_name, ingredient_unit)
VALUES ('i�Ұ�� ������ũ �ҽ�', 'g');

INSERT INTO Ingredient (ingredient_name, ingredient_unit)
VALUES ('i��Ͼ� ����', 'g');

INSERT INTO Ingredient (ingredient_name, ingredient_unit)
VALUES ('�ұ�', 'g');

INSERT INTO Ingredient (ingredient_name, ingredient_unit)
VALUES ('����', 'g');

INSERT INTO Ingredient (ingredient_name, ingredient_unit)
VALUES ('�߻�� ����', 'g');

INSERT INTO Ingredient (ingredient_name, ingredient_unit)
VALUES ('�ø��� ����', 'g');

INSERT INTO Ingredient (ingredient_name, ingredient_unit)
VALUES ('����', 'g');

INSERT INTO Ingredient (ingredient_name, ingredient_unit)
VALUES ('�а���', 'g');

INSERT INTO Ingredient (ingredient_name, ingredient_unit)
VALUES ('����', '��');

INSERT INTO Ingredient (ingredient_name, ingredient_unit)
VALUES ('���', 'g');

INSERT INTO Ingredient (ingredient_name, ingredient_unit)
VALUES ('����', 'g');

INSERT INTO Ingredient (ingredient_name, ingredient_unit)
VALUES ('����', 'g');

INSERT INTO Ingredient (ingredient_name, ingredient_unit)
VALUES ('�丶��', 'g');

INSERT INTO Ingredient (ingredient_name, ingredient_unit)
VALUES ('������� ��ä', 'g');

INSERT INTO Ingredient (ingredient_name, ingredient_unit)
VALUES ('�Ұ�� �Ƚ�', 'g');

INSERT INTO Ingredient (ingredient_name, ingredient_unit)
VALUES ('����', 'g');

INSERT INTO Ingredient (ingredient_name, ingredient_unit)
VALUES ('����', 'g');

INSERT INTO Ingredient (ingredient_name, ingredient_unit)
VALUES ('ġ��', 'g');

INSERT INTO Ingredient (ingredient_name, ingredient_unit)
VALUES ('����', 'ml');

INSERT INTO Ingredient (ingredient_name, ingredient_unit)
VALUES ('���', '��');

INSERT INTO Ingredient (ingredient_name, ingredient_unit)
VALUES ('����ī���� ġ��', 'g');

-- ��� ���̺�
-- ���ĸ����� ����, ������Ѽ����� ����
INSERT INTO Inventory (ingredient_name, inventory_quantity, expiry_date)
VALUES ('iƼ��̼�', 12, SYSDATE+4);

INSERT INTO Inventory (ingredient_name, inventory_quantity, expiry_date)
VALUES ('iũ�� �기', 12, SYSDATE+4);

INSERT INTO Inventory (ingredient_name, inventory_quantity, expiry_date)
VALUES ('i��Ͼ� ����', 30, SYSDATE+1);

INSERT INTO Inventory (ingredient_name, inventory_quantity, expiry_date)
VALUES ('�ұ�', 4000000, NULL);

INSERT INTO Inventory (ingredient_name, inventory_quantity, expiry_date)
VALUES ('����', 300000, NULL);

INSERT INTO Inventory (ingredient_name, inventory_quantity, expiry_date)
VALUES ('�߻�� ����', 1000, SYSDATE+600);

INSERT INTO Inventory (ingredient_name, inventory_quantity, expiry_date)
VALUES ('�ø��� ����', 300000, SYSDATE+80);

INSERT INTO Inventory (ingredient_name, inventory_quantity, expiry_date)
VALUES ('����', 300000, SYSDATE+120);

INSERT INTO Inventory (ingredient_name, inventory_quantity, expiry_date)
VALUES ('i�巹��', 2000, SYSDATE+3);

INSERT INTO Inventory (ingredient_name, inventory_quantity, expiry_date)
VALUES ('�а���', 20000, SYSDATE+300);

INSERT INTO Inventory (ingredient_name, inventory_quantity, expiry_date)
VALUES ('�а���', 70000, SYSDATE+80);

INSERT INTO Inventory (ingredient_name, inventory_quantity, expiry_date)
VALUES ('�а���', 2000, SYSDATE+20);

INSERT INTO Inventory (ingredient_name, inventory_quantity, expiry_date)
VALUES ('i�Ľ�Ÿ', 3000, SYSDATE+1);

INSERT INTO Inventory (ingredient_name, inventory_quantity, expiry_date)
VALUES ('i��', 30, SYSDATE +1);

INSERT INTO Inventory (ingredient_name, inventory_quantity, expiry_date)
VALUES ('����', 4000, SYSDATE+15);

INSERT INTO Inventory (ingredient_name, inventory_quantity, expiry_date)
VALUES ('���', 4000, SYSDATE+15);

INSERT INTO Inventory (ingredient_name, inventory_quantity, expiry_date)
VALUES ('����', 4000, SYSDATE+15);

INSERT INTO Inventory (ingredient_name, inventory_quantity, expiry_date)
VALUES ('����', 4000, SYSDATE+15);

INSERT INTO Inventory (ingredient_name, inventory_quantity, expiry_date)
VALUES ('�丶��', 4000, SYSDATE+15);

INSERT INTO Inventory (ingredient_name, inventory_quantity, expiry_date)
VALUES ('������� ��ä', 3000, SYSDATE+7);

INSERT INTO Inventory (ingredient_name, inventory_quantity, expiry_date)
VALUES ('�Ұ�� �Ƚ�', 60000, SYSDATE+5);

INSERT INTO Inventory (ingredient_name, inventory_quantity, expiry_date)
VALUES ('����', 60000, SYSDATE+5);

INSERT INTO Inventory (ingredient_name, inventory_quantity, expiry_date)
VALUES ('i���� ������ũ �ҽ�', 9000, SYSDATE+5);

INSERT INTO Inventory (ingredient_name, inventory_quantity, expiry_date)
VALUES ('i�Ұ�� ������ũ �ҽ�', 9000, SYSDATE+5);

INSERT INTO Inventory (ingredient_name, inventory_quantity, expiry_date)
VALUES ('����', 30000, SYSDATE+5);

INSERT INTO Inventory (ingredient_name, inventory_quantity, expiry_date)
VALUES ('ġ��', 30000, SYSDATE+5);

INSERT INTO Inventory (ingredient_name, inventory_quantity, expiry_date)
VALUES ('����', 30000, SYSDATE+5);

INSERT INTO Inventory (ingredient_name, inventory_quantity, expiry_date)
VALUES ('���', 30000, SYSDATE+5);

INSERT INTO Inventory (ingredient_name, inventory_quantity, expiry_date)
VALUES ('����ī���� ġ��', 5000, SYSDATE+5);

-- ������ǥ
INSERT INTO Recipe (food_name, ingredient_name, recipe_quantity)
VALUES('��', 'i��', 1);

INSERT INTO Recipe (food_name, ingredient_name, recipe_quantity)
VALUES('����ġ ��Ͼ� ����S', 'i��Ͼ� ����', 1);

INSERT INTO Recipe (food_name, ingredient_name, recipe_quantity)
VALUES('����ġ ��Ͼ� ����S', 'ġ��', 30);

INSERT INTO Recipe (food_name, ingredient_name, recipe_quantity)
VALUES('����ġ ��Ͼ� ����L', 'i��Ͼ� ����', 4);

INSERT INTO Recipe (food_name, ingredient_name, recipe_quantity)
VALUES('����ġ ��Ͼ� ����L', 'ġ��', 120);

INSERT INTO Recipe (food_name, ingredient_name, recipe_quantity)
VALUES ('�׸� ������', '������� ��ä', 200);

INSERT INTO Recipe (food_name, ingredient_name, recipe_quantity)
VALUES ('�׸� ������', 'i�巹��', 30);

INSERT INTO Recipe (food_name, ingredient_name, recipe_quantity)
VALUES ('���� ������', '����', 300);

INSERT INTO Recipe (food_name, ingredient_name, recipe_quantity)
VALUES ('���� ������', '������� ��ä', 200);

INSERT INTO Recipe (food_name, ingredient_name, recipe_quantity)
VALUES ('���� ������', 'i�巹��', 30);

INSERT INTO Recipe (food_name, ingredient_name, recipe_quantity)
VALUES ('���� ������ũ', '����', 300);

INSERT INTO Recipe (food_name, ingredient_name, recipe_quantity)
VALUES ('���� ������ũ', 'i���� ������ũ �ҽ�', 50);

INSERT INTO Recipe (food_name, ingredient_name, recipe_quantity)
VALUES ('�Ƚ� ������ũ', '�Ұ�� �Ƚ�', 200);

INSERT INTO Recipe (food_name, ingredient_name, recipe_quantity)
VALUES ('�Ƚ� ������ũ', 'i�Ұ�� ������ũ �ҽ�', 50);

INSERT INTO Recipe (food_name, ingredient_name, recipe_quantity)
VALUES ('�丶�� �Ƚ� �Ľ�Ÿ', 'i�Ľ�Ÿ', 150);

INSERT INTO Recipe (food_name, ingredient_name, recipe_quantity)
VALUES ('�丶�� �Ƚ� �Ľ�Ÿ', '�Ұ�� �Ƚ�', 200);

INSERT INTO Recipe (food_name, ingredient_name, recipe_quantity)
VALUES ('ũ�� �ҽ� ���� �Ľ�Ÿ', 'i�Ľ�Ÿ', 150);

INSERT INTO Recipe (food_name, ingredient_name, recipe_quantity)
VALUES ('ũ�� �ҽ� ���� �Ľ�Ÿ', '����', 200);

INSERT INTO Recipe (food_name, ingredient_name, recipe_quantity)
VALUES ('ũ�� �ҽ� ���� �Ľ�Ÿ', '����', 100);

INSERT INTO Recipe (food_name, ingredient_name, recipe_quantity)
VALUES ('ũ�� �ҽ� ���� �Ľ�Ÿ', '����', 200);

INSERT INTO Recipe (food_name, ingredient_name, recipe_quantity)
VALUES ('Ƽ��̼�', 'iƼ��̼�', 1);



-- i��Ͼ� ���� ������
-- iƼ��̼� ������
INSERT INTO iRecipe (r_ingredient_name, i_ingredient_name, r_recipe_quantity, i_recipe_quantity)
VALUES('iƼ��̼�', '���', 1, 6);

INSERT INTO iRecipe (r_ingredient_name, i_ingredient_name, r_recipe_quantity, i_recipe_quantity)
VALUES('iƼ��̼�', '����', 1, 100);

INSERT INTO iRecipe (r_ingredient_name, i_ingredient_name, r_recipe_quantity, i_recipe_quantity)
VALUES('iƼ��̼�', '����ī���� ġ��', 1, 250);

-- i�巹�� ������
INSERT INTO iRecipe (r_ingredient_name, i_ingredient_name, r_recipe_quantity, i_recipe_quantity)
VALUES('i�巹��', '�ø��� ����', 100, 60);

INSERT INTO iRecipe (r_ingredient_name, i_ingredient_name, r_recipe_quantity, i_recipe_quantity)
VALUES('i�巹��', '�߻�� ����', 100, 40);

INSERT INTO iRecipe (r_ingredient_name, i_ingredient_name, r_recipe_quantity, i_recipe_quantity)
VALUES('i�巹��', '�ұ�', 100, 10);

INSERT INTO iRecipe (r_ingredient_name, i_ingredient_name, r_recipe_quantity, i_recipe_quantity)
VALUES('i�巹��', '����', 100, 10);

-- i�Ľ�Ÿ ������
INSERT INTO iRecipe (r_ingredient_name, i_ingredient_name, r_recipe_quantity, i_recipe_quantity)
VALUES('i�Ľ�Ÿ', '�ұ�', 100, 10);

INSERT INTO iRecipe (r_ingredient_name, i_ingredient_name, r_recipe_quantity, i_recipe_quantity)
VALUES('i�Ľ�Ÿ', '�а���', 100, 60);

-- i���� ������ũ �ҽ� ������
INSERT INTO iRecipe (r_ingredient_name, i_ingredient_name, r_recipe_quantity, i_recipe_quantity)
VALUES('i���� ������ũ �ҽ�', '�ø��� ����', 100, 30);

INSERT INTO iRecipe (r_ingredient_name, i_ingredient_name, r_recipe_quantity, i_recipe_quantity)
VALUES('i���� ������ũ �ҽ�', '�ұ�', 100, 10);

-- i�Ұ�� ������ũ �ҽ� ������
INSERT INTO iRecipe (r_ingredient_name, i_ingredient_name, r_recipe_quantity, i_recipe_quantity)
VALUES('i�Ұ�� ������ũ �ҽ�', '�ұ�', 100, 10);

INSERT INTO iRecipe (r_ingredient_name, i_ingredient_name, r_recipe_quantity, i_recipe_quantity)
VALUES('i�Ұ�� ������ũ �ҽ�', '����', 100, 10);

-- i��Ͼ� ���� ������
INSERT INTO iRecipe (r_ingredient_name, i_ingredient_name, r_recipe_quantity, i_recipe_quantity)
VALUES('i��Ͼ� ����', 'i��', 100, 1);

INSERT INTO iRecipe (r_ingredient_name, i_ingredient_name, r_recipe_quantity, i_recipe_quantity)
VALUES('i��Ͼ� ����', '����', 100, 100);

INSERT INTO iRecipe (r_ingredient_name, i_ingredient_name, r_recipe_quantity, i_recipe_quantity)
VALUES('i��Ͼ� ����', '����', 100, 50);

INSERT INTO iRecipe (r_ingredient_name, i_ingredient_name, r_recipe_quantity, i_recipe_quantity)
VALUES('i��Ͼ� ����', '�ұ�', 100, 10);

INSERT INTO iRecipe (r_ingredient_name, i_ingredient_name, r_recipe_quantity, i_recipe_quantity)
VALUES('i��Ͼ� ����', '����', 100, 10);


-- �� ������
INSERT INTO iRecipe (r_ingredient_name, i_ingredient_name, r_recipe_quantity, i_recipe_quantity)
VALUES('i��', '���', 100, 50);

INSERT INTO iRecipe (r_ingredient_name, i_ingredient_name, r_recipe_quantity, i_recipe_quantity)
VALUES('i��', '����', 100, 100);

INSERT INTO iRecipe (r_ingredient_name, i_ingredient_name, r_recipe_quantity, i_recipe_quantity)
VALUES('i��', '�а���', 100, 1500);


SELECT * FROM Menu;

SELECT * FROM Ingredient;

SELECT * FROM Inventory;

SELECT * FROM Recipe;

SELECT * FROM iRecipe;