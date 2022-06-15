<div class="partners-container">
    <div class="owl-carousel owl-theme" id="partnersCarousel">
        <?php
        $banks = array("acba", "vtb", "ameria", "ineco", "id", "evoca", "ardshin","unibank");

        for ($i = 0; $i < count($banks); $i++) { ?>
            <div>
                <div class="item block-container">
                    <a href="" class="flex align-items-center justify-center">
                        <img src="../images/banks/logo/<?= $banks[$i]?>.svg" />
                    </a>
                </div>
                <div class="item block-container">
                    <a href="" class="flex align-items-center justify-center">
                        <img src="../images/banks/logo/<?= $banks[$i]?>.svg" />
                    </a>
                </div>
            </div>
        <?php } ?>
    </div>
</div>