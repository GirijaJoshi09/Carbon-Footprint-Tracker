# Carbon-Footprint-Tracker
# 🌍 Carbon Footprint Tracker

A Java-based console application to help users track, calculate, and reduce their personal and household carbon emissions.

---

## 📌 Project Description

The **Carbon Footprint Tracker** allows users to:
- Sign up or log in securely.
- Enter details of their **household energy usage**, **lifestyle habits**, and **travel patterns**.
- Get a breakdown of their CO₂ emissions using activity-specific emission rates.
- View suggestions to reduce their environmental impact.

---

## 🚀 Features

### ✅ Authentication
- **Sign Up** and **Login** with username/password.
- User-wise data tracking using a custom `MyHashMap`.

### ✅ Household Emissions
- Calculates emissions from:
  - 🔌 Electricity
  - 🔥 Natural Gas, LPG, Heating Oil, Coal
  - 🏠 Propane
  
### ✅ Secondary Emissions (Spending Habits)
- Estimates CO₂ output from:
  - 🍽️ Diet (Vegetarian / Meat Eater)
  - 💊 Pharmaceuticals
  - 👕 Clothing & Paper
  - 💻 IT Equipment, 🎮 Recreation, 📞 Telecom, 📚 Education

### ✅ Travel Emissions
- Tracks travel habits across:
  - 🚗 Car
  - 🚴‍♀️ Bike
  - 🚌 Public Transport
  - ✈️ Flight

### ✅ Smart Suggestions
- Gives personalized recommendations based on highest-emission category.

### ✅ Data Structures Used
| Purpose                          | Data Structure   |
|----------------------------------|------------------|
| Store users                      | Custom `HashMap` |
| Store & compare activities       | `PriorityQueue`  |
| Group activities per user        | `ArrayList`      |

---

## 🧮 Sample Console Flow
***** Welcome to Carbon Foot Print Tracker 😊😊 *****

1. Sign Up
2. Login
3. Exit
Choose an option: 1

Enter the username to sign up: girija
Enter password: 1234
Sign-up successful! Enter the details :

Enter how many people are in your household : 4
Enter the amount of electricity in kWh : 1200
Enter the amount of natural gas in kWh : 500
Enter the amount of heating oil in litres : 100
Enter the amount of coal in kWh : 0
Enter the amount of LPG in litres : 60
Enter the amount of propane in litres : 50

Carbon Footprint Breakdown:
Electricity: 1104.0 kg CO2
Natural Gas: 92.5 kg CO2
Heating Oil: 26.9 kg CO2
LPG: 176.4 kg CO2
Propane: 126.0 kg CO2
Coal: 0.0 kg CO2

Total Carbon Footprint for 4 people: 1525.8 kg CO2

Choose your diet type (Vegetarian / Meat Eater): Vegetarian
Enter annual spending on Food and Drink: 5000
Enter annual spending on Pharmaceuticals: 1000
Enter annual spending on Clothes, textiles and shoes: 1500
Enter annual spending on Paper-based products: 500
Enter annual spending on Computers and IT equipment: 800
Enter annual spending on Television, radio and phone equipment: 400
Enter annual spending on Motor vehicles: 2000
Enter annual spending on Furniture and other manufactured goods: 1000
Enter annual spending on Hotels, restaurants, pubs: 1200
Enter annual spending on Telephone call costs: 300
Enter annual spending on Banking and finance: 1000
Enter annual spending on Insurance: 500
Enter annual spending on Education: 2000
Enter annual spending on Recreational and cultural activities: 1500

Carbon Footprint Breakdown:
Food and Drink (Vegetarian): 12500.0 kg CO2
Pharmaceuticals: 1900.0 kg CO2
Clothes and textiles: 3750.0 kg CO2
Paper-based products: 1100.0 kg CO2
Computers and IT equipment: 1440.0 kg CO2
TV, Radio, and Phone equipment: 600.0 kg CO2
Motor Vehicles: 4800.0 kg CO2
Furniture: 2000.0 kg CO2
Hotels and Restaurants: 3600.0 kg CO2
Telephone costs: 210.0 kg CO2
Banking and Finance: 1200.0 kg CO2
Insurance: 500.0 kg CO2
Education: 1800.0 kg CO2
Recreation and Cultural activities: 1650.0 kg CO2

Total Carbon Footprint for 4 people: 38150.0 kg CO2

Enter the days per week : 5
Enter the time you travel by car in hours : 2
Enter the time you travel by bike in hours : 0.5
Enter the time you travel by public transport in hours : 1
Enter the time you travel by flight in hours : 0

Weekly Carbon Footprint : 1.46 kg CO2

Carbon Footprint Breakdown:
Car: 1.2 kg CO2
Bike: 0.175 kg CO2
Public Transport: 0.2 kg CO2
Flight: 0.0 kg CO2

Suggestion: The carbon footprint for using a car is the highest.
✅ Using public transport reduces your carbon footprint by 83.33%.
✅ Using a bike will reduce your carbon footprint by 67.5%.
Consider using a public transport instead for a greener option!

------------Thank you for visiting us !! 🙏🙏 ----------------
