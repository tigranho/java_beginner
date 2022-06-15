<div class="padding-bt-60">
    <div class="inner-container">
        <p class="font-24 title margin-bottom-35">Հատուկ առաջարկներ</p>
        <div class="tab-container">
            <div class="tab-nav flex align-items-center flex-wrap">
                <span class="tab-link" onclick="openTabItem(event, 'deposit')" id="defaultOpen">Ավանդներ </span>
                <span class="tab-link" onclick="openTabItem(event, 'hyperpox')">Հիփոթեք</span>
                <span class="tab-link" onclick="openTabItem(event, 'consumer-loan')">Սպառողական վարկեր</span>
                <span class="tab-link" onclick="openTabItem(event, 'car-loan')">Ավտովարկ</span>
                <span class="tab-link" onclick="openTabItem(event, 'agricultural')">Գյուղատնտեսական վարկ</span>
                <span class="tab-link" onclick="openTabItem(event, 'card')">Քարտեր</span>
            </div>

            <div class="tab-content" id="deposit">
                <div class="top">
                    <a href="" class="green-link width-icon ellipsis width-percent-70 block">
                        <i class="icon-grid"></i>
                        <span>Ավանդների բոլոր առաջարկները</span>
                    </a>
                </div>
                <div>
                    <?php  include('inc/carousel.php') ?>
                </div>
            </div>
            <div class="tab-content" id="hyperpox">
                <div class="top">
                    <a href="" class="green-link width-icon">
                        <i class="icon-grid"></i>
                        <span>Հիփոթեքի բոլոր առաջարկները</span>
                    </a>
                </div>
                <div>
                    <?php  include('inc/carousel.php') ?>
                </div>
            </div>
        </div>
    </div>
</div>
