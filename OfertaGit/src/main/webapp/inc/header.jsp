<header>
    <div class="inner-container large">
        <div class="flex space-between">
           <div class="left flex align-items-center">
               <span class="logo">
                   <a href="index.php"><img src="../images/logo.svg" alt="" /></a>
               </span>
               <span class="hide-for-tablet">
                   <ul>
                       <li><a href="index.php?page=about-us">Մեր մասին</a></li>
                       <li><a href="index.php?page=contact">Կապ մեզ հետ</a></li>
                       <li><a href="index.php?page=banks">Մեր Գործընկերները</a></li>
                       <li><a href="index.php?page=usefull-links">Օգտակար հոդվածներ</a></li>
                   </ul>
               </span>
           </div>
           <div class="right flex align-items-center">
               <span class="state ellipsis hide-for-tablet"><i class="icon-state"></i> Երևան </span>
               <span class="language-box" >
                    <span class="selected-item" onclick="toggleBoxes('langList')">Հայ</span>
                    <div class="list" id="langList">
                         <ul>
                            <li class="active">Հայ</li>
                            <li>Eng</li>
                            <li>Рус</li>
                        </ul>
                    </div>
                </span>
                <span class="compere-box show-for-tablet relative">
                     <span class="compere-icon" onclick="toggleBoxes('compareTooltipMb')">
                        <span class="count">4</span>
                        <i class="icon-libra"></i>
                    </span>
                    <div class="tooltip-container bottom right" id="compareTooltipMb">
                        <div class="tooltip"> <!--Todo add 'tp-blue' class when there is no item -->
                            <span class="tooltip-title">Համեմատության</span>
                            <ul>
                                <li>
                                    <span>Սպառողական վարկ</span>
                                    <span class="bold font-14">4</span>
                                    <i class="icon-delete"></i>
                                </li>
                            </ul>
                            <!--Todo add when there is no item -->
                            <!--                            <span>Համեմատության էջում տեղ չկա</span>-->
                        </div>
                    </div>
                </span>
                <span class="i-menu show-for-tablet" id="mbNavBtn"><i class="icon-menu font-20"></i></span>
           </div>
        </div>
    </div>
</header>