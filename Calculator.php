<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns = "http://www.w3.org/1999/xhtml" xml:lang = "en">
    <head>
        
        <meta http-equiv = "content-type" content = "text/html; charset = utf-8" />
        <title> Calculator </title>  
        
    </head>
    <body>
        
        <?php
        
        function calculate_total($quantity, $price){
            $total = $quantity * $price;
            $total = number_format($total, 2);
            return $total;
        }
        
        if(isset($_POST['submitted'])){
            
            if(is_numeric($_POST['quantity']) && (is_numeric($_POST['price']))){
                $total = calculate_total($_POST['quantity'], $_POST['price']);
                print "<p>Your total comes to $<span style=\"font-weight: hold;\">$total</span>.</p>";
            }else{
                print '<p style="color: red;">Please enter a valid quantity and price!</p>';
            }
            
        }
        
        ?>
        
        <form action = "Calculator.php" method = "post">
            
            <p>Quantity : <input type = "text" name = "quantity" size = "3"/></p>
            <p>Price : <input type = "text" name = "price" size = "5"/></p>
            <input type = "submit" name = "submit" value = "Calculate!"/>
            <input type = "hidden" name = "submitted" value = "true"/>
            
        </form>
        
    </body>
</html>