## 剩隅攻略-有效期限管理器

111-1軟體工程_期末專案

1.	簡介
1.1規格目的
本規格的目的在於定義「剩隅攻略」軟體的各項功能，使用者可透本文件了解軟體的架構、作業流程及系統的各項功能及相關說明；系統之需求規格、標準應用、測試驗收等也將以本文件為依據。
1.2規格範圍
本規格的範圍主要是針對「剩隅攻略」的需求規格項目，包括系統前端設計、系統資料庫及系統後端的相關架構與功能。
2.	系統概述
2.1系統目標
到買場總會大肆購買商品，但往往回家後，有些商品只是放著，卻未使用，尤其是有有效期限的食物，我們每天都需要吃食物才能存活，但常常因為食物過期而煩惱嗎? 剩隅攻略是一個能有效控管購買商品有效期限的手機APP，在購買商品後將商品有效期限輸入APP，接下來只需定期察看APP，就能有效地去管理目前家中目前的商品，避免商品常常因忘記，而過期，造成不必要的浪費。
本軟體也結合了SDGs精神，朝著綠色經濟確保永續消費模式思考，透罐APP管理，避免使用者產生不必要的浪費，也進而促進使用者在生活中的飲食管理，不但減少了食物的浪費，也增加使用者的健康。
2.2系統描述
2.2.1使用者
提供使用者輸入商品有效期限，並進行管理。
2.2.2客服中心
提供客服中心查看並且管理及解決使用者提出的問題
2.3系統環境
作業軟體：Android Studio
資料庫(商品)：本地SQLite
資料庫(回饋)：Google Sheet
版本管理：Git
2.4系統使用角色
2.4.1使用者端
使用者可以透過此軟體輸入所購買的商品，並定期更新查看，可以有效地進行商品有效期限的管理，若有問題或建議，也可透過意見反映介面，向客服中心進行反映。
2.4.2客服中心端
可以透過Google Sheet了解使用者的反應資料，並進行處理及解決，並且透過使用者所提供的信箱進行回復。

3.	需求規格
3.1系統架構
3.1.1使用者端 
![image](https://github.com/xin-2001/FoodExpirationManager/assets/77916095/1d541ad6-60a7-447e-8439-e2669a2214f9)

3.1.2客服中心端
![image](https://github.com/xin-2001/FoodExpirationManager/assets/77916095/12b9cabb-baa8-4c96-93c3-45e7c17c39f6)

 
3.2後端資料表
![image](https://github.com/xin-2001/FoodExpirationManager/assets/77916095/220a4504-6284-4655-b9b4-deb0101ff9e9)

![image](https://github.com/xin-2001/FoodExpirationManager/assets/77916095/3aa78360-3269-4a3f-bb3d-db7755b99605)


3.3系統整體需求
Android：9.0以上
3.4功能需求
3.4.1使用者需求功能
![image](https://github.com/xin-2001/FoodExpirationManager/assets/77916095/16ec8e41-8e44-4f24-ac92-25c8971a7dba)

 
3.4.2客服中心需求功能
![image](https://github.com/xin-2001/FoodExpirationManager/assets/77916095/6a747486-0605-4980-a4a8-6a7e7b0699fe)


4.	系統測試
4.1測試資料說明與結果
4.1.1使用者端
(1)	新增商品
![image](https://github.com/xin-2001/FoodExpirationManager/assets/77916095/5811920c-8adc-4893-be3d-30358269d408)

(2)	從目錄至各頁面
![image](https://github.com/xin-2001/FoodExpirationManager/assets/77916095/da28a516-c6a4-4b56-bbc2-f342446f430e)

(3)	修改商品內容
![image](https://github.com/xin-2001/FoodExpirationManager/assets/77916095/1130e439-ae61-405e-8364-269a7ff9b5cc)

(4)	即時更新商品數量
![image](https://github.com/xin-2001/FoodExpirationManager/assets/77916095/5ded7182-bed1-4e5f-83ce-ccf0d14fa25e)

(5)	搜尋關鍵字查詢商品
![image](https://github.com/xin-2001/FoodExpirationManager/assets/77916095/3799eda1-1b46-4c9d-ae04-22fc8931d7f2)

(6)	使用內建篩選功能
![image](https://github.com/xin-2001/FoodExpirationManager/assets/77916095/813b2a49-d1fa-44ee-bf6f-1084e683392e)

(7)	意見反映
![image](https://github.com/xin-2001/FoodExpirationManager/assets/77916095/b630a941-a1c8-46a4-8b57-5cafc36023de)

4.1.2客服中心端
(1)	意見處理
![image](https://github.com/xin-2001/FoodExpirationManager/assets/77916095/36a9e366-58c6-4d9e-ba24-5d63bb16dc00)

