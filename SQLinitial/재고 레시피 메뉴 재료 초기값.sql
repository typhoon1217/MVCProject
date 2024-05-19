-- 메뉴표
INSERT INTO Menu (food_name, food_price, food_description, food_category) 
VALUES ('빵', 500, '올리브유와 발사믹소스와 함께 서빙되는 빵', 'Appetizer');

INSERT INTO Menu (food_name, food_price, food_description, food_category) 
VALUES ('프랜치 어니언 수프L', 10000, '고소한 양파 스프에 구운 빵과 치즈를 올린 프랑스식 양파 수프 500g 입니다.', 'Appetizer');

INSERT INTO Menu (food_name, food_price, food_description, food_category) 
VALUES ('프랜치 어니언 수프S', 5000, '고소한 양파 스프에 구운 빵과 치즈를 올린 프랑스식 양파 수프 150g 입니다.', 'Appetizer');

INSERT INTO Menu (food_name, food_price, food_description, food_category) 
VALUES ('그린 샐러드', 13000, '신선한 야채와 크루톤, 치즈를 드레싱으로 맛을 낸 그린 샐러드입니다.', 'Salad');

INSERT INTO Menu (food_name, food_price, food_description, food_category) 
VALUES ('연어 샐러드', 14000, '신선한 연어회 야채를 드레싱과 함께 내어드리는 건강한 연어 샐러드입니다', 'Salad');

INSERT INTO Menu (food_name, food_price, food_description, food_category) 
VALUES ('연어 스테이크', 25000, '신선한 연어를 그릴에 구워 각종 소스와 함께 내어드리는 그릴드 연어 스테이크입니다.', 'Seafood Dish');

INSERT INTO Menu (food_name, food_price, food_description, food_category) 
VALUES ('안심 스테이크', 25000, '부드럽고 신선한 소고기 스테이크입니다.', 'Meat Dish');

INSERT INTO Menu (food_name, food_price, food_description, food_category) 
VALUES ('토마토 안심 파스타', 13000, '신선한 토마토와 바삭한 소고기 안심을 넣어 만든 토마토 소고기 안심 파스타입니다.', 'Pasta');

INSERT INTO Menu (food_name, food_price, food_description, food_category) 
VALUES ('크림 소스 새우 파스타', 13000, '크림 소스로 맛을 낸 신선한 파스타입니다.', 'Pasta');

INSERT INTO Menu (food_name, food_price, food_description, food_category) 
VALUES ('티라미수', 8000, '부드러운 마스카포네 치즈와 커피 맛이 어우러진 이탈리안 대표 디저트, 티라미수입니다.', 'Dessert');

-- 재료표
INSERT INTO Ingredient (ingredient_name, ingredient_unit)
VALUES ('i티라미수', '개');

INSERT INTO Ingredient (ingredient_name, ingredient_unit)
VALUES ('i크림 브린', '개');

INSERT INTO Ingredient (ingredient_name, ingredient_unit)
VALUES ('i드레싱', 'g');

INSERT INTO Ingredient (ingredient_name, ingredient_unit)
VALUES ('i빵', 'g');

INSERT INTO Ingredient (ingredient_name, ingredient_unit)
VALUES ('i파스타', 'g');

INSERT INTO Ingredient (ingredient_name, ingredient_unit)
VALUES ('i연어 스테이크 소스', 'g');

INSERT INTO Ingredient (ingredient_name, ingredient_unit)
VALUES ('i소고기 스테이크 소스', 'g');

INSERT INTO Ingredient (ingredient_name, ingredient_unit)
VALUES ('i어니언 수프', 'g');

INSERT INTO Ingredient (ingredient_name, ingredient_unit)
VALUES ('소금', 'g');

INSERT INTO Ingredient (ingredient_name, ingredient_unit)
VALUES ('설탕', 'g');

INSERT INTO Ingredient (ingredient_name, ingredient_unit)
VALUES ('발사믹 식초', 'g');

INSERT INTO Ingredient (ingredient_name, ingredient_unit)
VALUES ('올리브 오일', 'g');

INSERT INTO Ingredient (ingredient_name, ingredient_unit)
VALUES ('버터', 'g');

INSERT INTO Ingredient (ingredient_name, ingredient_unit)
VALUES ('밀가루', 'g');

INSERT INTO Ingredient (ingredient_name, ingredient_unit)
VALUES ('양파', '개');

INSERT INTO Ingredient (ingredient_name, ingredient_unit)
VALUES ('당근', 'g');

INSERT INTO Ingredient (ingredient_name, ingredient_unit)
VALUES ('감자', 'g');

INSERT INTO Ingredient (ingredient_name, ingredient_unit)
VALUES ('오이', 'g');

INSERT INTO Ingredient (ingredient_name, ingredient_unit)
VALUES ('토마토', 'g');

INSERT INTO Ingredient (ingredient_name, ingredient_unit)
VALUES ('샐러드용 야채', 'g');

INSERT INTO Ingredient (ingredient_name, ingredient_unit)
VALUES ('소고기 안심', 'g');

INSERT INTO Ingredient (ingredient_name, ingredient_unit)
VALUES ('연어', 'g');

INSERT INTO Ingredient (ingredient_name, ingredient_unit)
VALUES ('새우', 'g');

INSERT INTO Ingredient (ingredient_name, ingredient_unit)
VALUES ('치즈', 'g');

INSERT INTO Ingredient (ingredient_name, ingredient_unit)
VALUES ('우유', 'ml');

INSERT INTO Ingredient (ingredient_name, ingredient_unit)
VALUES ('계란', '개');

INSERT INTO Ingredient (ingredient_name, ingredient_unit)
VALUES ('마스카포네 치즈', 'g');

-- 재고 테이블
-- 음식명으로 정렬, 유통기한순으로 정렬
INSERT INTO Inventory (ingredient_name, inventory_quantity, expiry_date)
VALUES ('i티라미수', 12, SYSDATE+4);

INSERT INTO Inventory (ingredient_name, inventory_quantity, expiry_date)
VALUES ('i크림 브린', 12, SYSDATE+4);

INSERT INTO Inventory (ingredient_name, inventory_quantity, expiry_date)
VALUES ('i어니언 수프', 30, SYSDATE+1);

INSERT INTO Inventory (ingredient_name, inventory_quantity, expiry_date)
VALUES ('소금', 4000000, NULL);

INSERT INTO Inventory (ingredient_name, inventory_quantity, expiry_date)
VALUES ('설탕', 300000, NULL);

INSERT INTO Inventory (ingredient_name, inventory_quantity, expiry_date)
VALUES ('발사믹 식초', 1000, SYSDATE+600);

INSERT INTO Inventory (ingredient_name, inventory_quantity, expiry_date)
VALUES ('올리브 오일', 300000, SYSDATE+80);

INSERT INTO Inventory (ingredient_name, inventory_quantity, expiry_date)
VALUES ('버터', 300000, SYSDATE+120);

INSERT INTO Inventory (ingredient_name, inventory_quantity, expiry_date)
VALUES ('i드레싱', 2000, SYSDATE+3);

INSERT INTO Inventory (ingredient_name, inventory_quantity, expiry_date)
VALUES ('밀가루', 20000, SYSDATE+300);

INSERT INTO Inventory (ingredient_name, inventory_quantity, expiry_date)
VALUES ('밀가루', 70000, SYSDATE+80);

INSERT INTO Inventory (ingredient_name, inventory_quantity, expiry_date)
VALUES ('밀가루', 2000, SYSDATE+20);

INSERT INTO Inventory (ingredient_name, inventory_quantity, expiry_date)
VALUES ('i파스타', 3000, SYSDATE+1);

INSERT INTO Inventory (ingredient_name, inventory_quantity, expiry_date)
VALUES ('i빵', 30, SYSDATE +1);

INSERT INTO Inventory (ingredient_name, inventory_quantity, expiry_date)
VALUES ('양파', 4000, SYSDATE+15);

INSERT INTO Inventory (ingredient_name, inventory_quantity, expiry_date)
VALUES ('당근', 4000, SYSDATE+15);

INSERT INTO Inventory (ingredient_name, inventory_quantity, expiry_date)
VALUES ('감자', 4000, SYSDATE+15);

INSERT INTO Inventory (ingredient_name, inventory_quantity, expiry_date)
VALUES ('오이', 4000, SYSDATE+15);

INSERT INTO Inventory (ingredient_name, inventory_quantity, expiry_date)
VALUES ('토마토', 4000, SYSDATE+15);

INSERT INTO Inventory (ingredient_name, inventory_quantity, expiry_date)
VALUES ('샐러드용 야채', 3000, SYSDATE+7);

INSERT INTO Inventory (ingredient_name, inventory_quantity, expiry_date)
VALUES ('소고기 안심', 60000, SYSDATE+5);

INSERT INTO Inventory (ingredient_name, inventory_quantity, expiry_date)
VALUES ('연어', 60000, SYSDATE+5);

INSERT INTO Inventory (ingredient_name, inventory_quantity, expiry_date)
VALUES ('i연어 스테이크 소스', 9000, SYSDATE+5);

INSERT INTO Inventory (ingredient_name, inventory_quantity, expiry_date)
VALUES ('i소고기 스테이크 소스', 9000, SYSDATE+5);

INSERT INTO Inventory (ingredient_name, inventory_quantity, expiry_date)
VALUES ('새우', 30000, SYSDATE+5);

INSERT INTO Inventory (ingredient_name, inventory_quantity, expiry_date)
VALUES ('치즈', 30000, SYSDATE+5);

INSERT INTO Inventory (ingredient_name, inventory_quantity, expiry_date)
VALUES ('우유', 30000, SYSDATE+5);

INSERT INTO Inventory (ingredient_name, inventory_quantity, expiry_date)
VALUES ('계란', 30000, SYSDATE+5);

INSERT INTO Inventory (ingredient_name, inventory_quantity, expiry_date)
VALUES ('마스카포네 치즈', 5000, SYSDATE+5);

-- 레시피표
INSERT INTO Recipe (food_name, ingredient_name, recipe_quantity)
VALUES('빵', 'i빵', 1);

INSERT INTO Recipe (food_name, ingredient_name, recipe_quantity)
VALUES('프랜치 어니언 수프S', 'i어니언 수프', 1);

INSERT INTO Recipe (food_name, ingredient_name, recipe_quantity)
VALUES('프랜치 어니언 수프S', '치즈', 30);

INSERT INTO Recipe (food_name, ingredient_name, recipe_quantity)
VALUES('프랜치 어니언 수프L', 'i어니언 수프', 4);

INSERT INTO Recipe (food_name, ingredient_name, recipe_quantity)
VALUES('프랜치 어니언 수프L', '치즈', 120);

INSERT INTO Recipe (food_name, ingredient_name, recipe_quantity)
VALUES ('그린 샐러드', '샐러드용 야채', 200);

INSERT INTO Recipe (food_name, ingredient_name, recipe_quantity)
VALUES ('그린 샐러드', 'i드레싱', 30);

INSERT INTO Recipe (food_name, ingredient_name, recipe_quantity)
VALUES ('연어 샐러드', '연어', 300);

INSERT INTO Recipe (food_name, ingredient_name, recipe_quantity)
VALUES ('연어 샐러드', '샐러드용 야채', 200);

INSERT INTO Recipe (food_name, ingredient_name, recipe_quantity)
VALUES ('연어 샐러드', 'i드레싱', 30);

INSERT INTO Recipe (food_name, ingredient_name, recipe_quantity)
VALUES ('연어 스테이크', '연어', 300);

INSERT INTO Recipe (food_name, ingredient_name, recipe_quantity)
VALUES ('연어 스테이크', 'i연어 스테이크 소스', 50);

INSERT INTO Recipe (food_name, ingredient_name, recipe_quantity)
VALUES ('안심 스테이크', '소고기 안심', 200);

INSERT INTO Recipe (food_name, ingredient_name, recipe_quantity)
VALUES ('안심 스테이크', 'i소고기 스테이크 소스', 50);

INSERT INTO Recipe (food_name, ingredient_name, recipe_quantity)
VALUES ('토마토 안심 파스타', 'i파스타', 150);

INSERT INTO Recipe (food_name, ingredient_name, recipe_quantity)
VALUES ('토마토 안심 파스타', '소고기 안심', 200);

INSERT INTO Recipe (food_name, ingredient_name, recipe_quantity)
VALUES ('크림 소스 새우 파스타', 'i파스타', 150);

INSERT INTO Recipe (food_name, ingredient_name, recipe_quantity)
VALUES ('크림 소스 새우 파스타', '새우', 200);

INSERT INTO Recipe (food_name, ingredient_name, recipe_quantity)
VALUES ('크림 소스 새우 파스타', '우유', 100);

INSERT INTO Recipe (food_name, ingredient_name, recipe_quantity)
VALUES ('크림 소스 새우 파스타', '버터', 200);

INSERT INTO Recipe (food_name, ingredient_name, recipe_quantity)
VALUES ('티라미수', 'i티라미수', 1);



-- i어니언 수프 레시피
-- i티라미수 레시피
INSERT INTO iRecipe (r_ingredient_name, i_ingredient_name, r_recipe_quantity, i_recipe_quantity)
VALUES('i티라미수', '계란', 1, 6);

INSERT INTO iRecipe (r_ingredient_name, i_ingredient_name, r_recipe_quantity, i_recipe_quantity)
VALUES('i티라미수', '설탕', 1, 100);

INSERT INTO iRecipe (r_ingredient_name, i_ingredient_name, r_recipe_quantity, i_recipe_quantity)
VALUES('i티라미수', '마스카포네 치즈', 1, 250);

-- i드레싱 레시피
INSERT INTO iRecipe (r_ingredient_name, i_ingredient_name, r_recipe_quantity, i_recipe_quantity)
VALUES('i드레싱', '올리브 오일', 100, 60);

INSERT INTO iRecipe (r_ingredient_name, i_ingredient_name, r_recipe_quantity, i_recipe_quantity)
VALUES('i드레싱', '발사믹 식초', 100, 40);

INSERT INTO iRecipe (r_ingredient_name, i_ingredient_name, r_recipe_quantity, i_recipe_quantity)
VALUES('i드레싱', '소금', 100, 10);

INSERT INTO iRecipe (r_ingredient_name, i_ingredient_name, r_recipe_quantity, i_recipe_quantity)
VALUES('i드레싱', '설탕', 100, 10);

-- i파스타 레시피
INSERT INTO iRecipe (r_ingredient_name, i_ingredient_name, r_recipe_quantity, i_recipe_quantity)
VALUES('i파스타', '소금', 100, 10);

INSERT INTO iRecipe (r_ingredient_name, i_ingredient_name, r_recipe_quantity, i_recipe_quantity)
VALUES('i파스타', '밀가루', 100, 60);

-- i연어 스테이크 소스 레시피
INSERT INTO iRecipe (r_ingredient_name, i_ingredient_name, r_recipe_quantity, i_recipe_quantity)
VALUES('i연어 스테이크 소스', '올리브 오일', 100, 30);

INSERT INTO iRecipe (r_ingredient_name, i_ingredient_name, r_recipe_quantity, i_recipe_quantity)
VALUES('i연어 스테이크 소스', '소금', 100, 10);

-- i소고기 스테이크 소스 레시피
INSERT INTO iRecipe (r_ingredient_name, i_ingredient_name, r_recipe_quantity, i_recipe_quantity)
VALUES('i소고기 스테이크 소스', '소금', 100, 10);

INSERT INTO iRecipe (r_ingredient_name, i_ingredient_name, r_recipe_quantity, i_recipe_quantity)
VALUES('i소고기 스테이크 소스', '설탕', 100, 10);

-- i어니언 수프 레시피
INSERT INTO iRecipe (r_ingredient_name, i_ingredient_name, r_recipe_quantity, i_recipe_quantity)
VALUES('i어니언 수프', 'i빵', 100, 1);

INSERT INTO iRecipe (r_ingredient_name, i_ingredient_name, r_recipe_quantity, i_recipe_quantity)
VALUES('i어니언 수프', '양파', 100, 100);

INSERT INTO iRecipe (r_ingredient_name, i_ingredient_name, r_recipe_quantity, i_recipe_quantity)
VALUES('i어니언 수프', '버터', 100, 50);

INSERT INTO iRecipe (r_ingredient_name, i_ingredient_name, r_recipe_quantity, i_recipe_quantity)
VALUES('i어니언 수프', '소금', 100, 10);

INSERT INTO iRecipe (r_ingredient_name, i_ingredient_name, r_recipe_quantity, i_recipe_quantity)
VALUES('i어니언 수프', '설탕', 100, 10);


-- 빵 레시피
INSERT INTO iRecipe (r_ingredient_name, i_ingredient_name, r_recipe_quantity, i_recipe_quantity)
VALUES('i빵', '계란', 100, 50);

INSERT INTO iRecipe (r_ingredient_name, i_ingredient_name, r_recipe_quantity, i_recipe_quantity)
VALUES('i빵', '버터', 100, 100);

INSERT INTO iRecipe (r_ingredient_name, i_ingredient_name, r_recipe_quantity, i_recipe_quantity)
VALUES('i빵', '밀가루', 100, 1500);


SELECT * FROM Menu;

SELECT * FROM Ingredient;

SELECT * FROM Inventory;

SELECT * FROM Recipe;

SELECT * FROM iRecipe;