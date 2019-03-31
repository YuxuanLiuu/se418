# Task 1: 利用chrome浏览器研究交大官网


## 研究对象


### 加载时间

在寝室网条件下，DOMContentLoad平均时间为800ms，完全加载（包括css，js，图片等静态文件）平均需要3.5s。列出了部分影响时间的图片文件（下载时间指不考虑阻塞时间，仅下载文件所用时间）：
| 文件           | 下载时间（ms）  |
| ------------- |:-------------:|  
| qsxLibrary.png    | 258      |  
|   oldbuilding.png    |    252 |
| column-1.jpg (石狮子图片) | 266   |

建议压缩这些图片清晰度提高网站速度。

### html页面

 上海交通大学logo图片未写alt。
源代码
```
<img src="https://www.sjtu.edu.cn/resource/assets/img/LogoWhite.png">
```

### 仍在使用HTTP/1.1
根据[google best practice](https://developers.google.com/web/tools/lighthouse/audits/http2)
HTTP/2可以更快，更省数据量地访问资源。

交大官网大部分资源仍使用http/1.1协议
以下是部分例子
```
https://www.sjtu.edu.cn

…ETUI/ETUI3.min.css(www.sjtu.edu.cn)

…ETUI/ETUI3.Utility.css(www.sjtu.edu.cn)

…OwlCarousel/owl.carousel.css(www.sjtu.edu.cn)

…OwlCarousel/owl.theme.default.css(www.sjtu.edu.cn)

…css/keyframes.css(www.sjtu.edu.cn)

...
```

### 使用了document.write()

根据[google best practice](https://developers.google.com/web/tools/lighthouse/audits/document-write) document.write() 对于使用2G，3G等网络质量较差的用户，document.write()将极大的降低页面渲染速度。

而交大官网页面下/resource/assets/js/headersy.js中通篇使用了document.write()。

### 无meta description

meta description可以有益于搜索引擎更精确找到页面。

### 应用数据储存

Explain how to run the automated tests for this system

### Break down into end to end tests

Explain what these tests test and why

```
Give an example
```

### And coding style tests

Explain what these tests test and why

```
Give an example
```

## Deployment

Add additional notes about how to deploy this on a live system

## Built With

* [Dropwizard](http://www.dropwizard.io/1.0.2/docs/) - The web framework used
* [Maven](https://maven.apache.org/) - Dependency Management
* [ROME](https://rometools.github.io/rome/) - Used to generate RSS Feeds

## Contributing

Please read [CONTRIBUTING.md](https://gist.github.com/PurpleBooth/b24679402957c63ec426) for details on our code of conduct, and the process for submitting pull requests to us.

## Versioning

We use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/your/project/tags). 

## Authors

* **Billie Thompson** - *Initial work* - [PurpleBooth](https://github.com/PurpleBooth)

See also the list of [contributors](https://github.com/your/project/contributors) who participated in this project.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* Hat tip to anyone whose code was used
* Inspiration
* etc

