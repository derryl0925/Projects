weight = 4.8

# Ground Shipping
flat_charge = 20.00
cost = 0
cost += flat_charge
price_per_pound = 0
if weight <= 2:
  price_per_pound = 1.50
  cost = cost + (weight * price_per_pound)
  print("Ground Shipping cost:", cost)
elif weight > 2 and weight <= 6:
  price_per_pound = 3.00
  cost = cost + (weight * price_per_pound)
  print("Ground Shipping cost:", cost)
elif weight > 6 and weight <= 10:
  price_per_pound = 4.00
  cost = cost + (weight * price_per_pound)
  print("Ground Shipping cost:", cost)
else:
  price_per_pound = 4.75
  cost = cost + (weight * price_per_pound)
  print("Ground Shipping cost:", cost)

# Premium Ground Shipping
premium_ground_shipping_flat_charge = 125.00
print("Premium Shipping cost:", premium_ground_shipping_flat_charge)

# Drone Shipping
drone_shipping_cost = 0
drone_price_per_pound = 0
drone_cost = 0
if weight <= 2:
  drone_price_per_pound = 4.50
  drone_cost = drone_cost + (weight * drone_price_per_pound)
  print("Drone Shipping cost:", drone_cost)
elif weight > 2 and weight <= 6:
 drone_price_per_pound = 9.00
 drone_cost = drone_cost + (weight * drone_price_per_pound)
 print("Drone Shipping cost:", drone_cost)
elif weight > 6 and weight <= 10:
  drone_price_per_pound = 12.00
  drone_cost = drone_cost + (weight * drone_price_per_pound)
  print("Drone Shipping cost:", drone_cost)
else:
  drone_price_per_pound = 14.25
  drone_cost = drone_cost + (weight * drone_price_per_pound)
  print("Drone Shipping cost:", drone_cost)

#Ground Shipping cost: 34.4
#Premium Shipping cost: 125.0
#Drone Shipping cost: 43.199999999999996