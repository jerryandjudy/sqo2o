//脚本幻灯片
//
//eleId : 界面容器id
//
function SlideAd(eleId, speedSeconds)
{
    var speedSeconds = speedSeconds || 5; //默认5秒变动一次
    var AdElement = document.getElementById(eleId);
    var height = AdElement.offsetHeight;
    if (AdElement.addEventListener) {
        AdElement.addEventListener('click', function (event) {
            var target = event.target || event.srcElement;
            if (target != null) {
                if (target.tagName == "LI") {
                    var index = parseInt(target.innerHTML) - 1;
                    AdElement.Move(index);
                    AdElement.Index = (index + 1) % AdElement.getElementsByTagName("li").length;
                }
            }
        }, false);
    }
    if (AdElement.attachEvent) {
        AdElement.attachEvent('onclick', function (event) {
            var target = event.target || event.srcElement;
            if (target != null) {
                if (target.tagName == "LI") {
                    var index = parseInt(target.innerHTML) - 1;
                    AdElement.Move(index);
                    AdElement.Index = (index + 1) % AdElement.getElementsByTagName("li").length;
                }
            }
        });
    }

    AdElement.Move = function (index) {
        var index = index || 0;
        this.Stop();
        var panel = this.getElementsByTagName("div")[1];
        var top = index * height;
        var list = this.getElementsByTagName("li");
        list[index].setAttribute("class", "Activate");
        list[index].setAttribute("className", "Activate");
        for (var i = 0; i < list.length; i++) {
            if (index != i) {
                list[i].removeAttribute("class");
                list[i].removeAttribute("className");
            }
        }
        this.SlideHandle = window.setInterval(function () { AdElement.Slide(panel, top); }, 10);
    };


    AdElement.MoveHandle = window.setInterval(function () {
        var index = AdElement.Index || 0;
        AdElement.Move(index);
        AdElement.Index = (index + 1) % AdElement.getElementsByTagName("li").length;
    }, speedSeconds * 1000);

    AdElement.Stop = function () {
        if (this.SlideHandle != null) {
            window.clearInterval(this.SlideHandle);
        }
    };

    AdElement.Slide = function (panel, top) {
        if (panel.scrollTop < top)
        {
            var step = Math.floor((top - panel.scrollTop) / 20);
            if (step < 10) {
                panel.scrollTop = panel.scrollTop + 1;
            }
            else {
                panel.scrollTop = panel.scrollTop + step;
            }
        }
        else if (panel.scrollTop > top) {
            var step = Math.floor((panel.scrollTop - top) / 20);
            if (step < 10) {
                panel.scrollTop = panel.scrollTop - 1;
            }
            else {
                panel.scrollTop = panel.scrollTop - step;
            }
        }
        else {
            this.Stop();
        }
    };
}