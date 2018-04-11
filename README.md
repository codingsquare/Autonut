# Autonut
[![FOSSA Status](https://app.fossa.io/api/projects/git%2Bgithub.com%2Fcodingsquare%2FAutonut.svg?type=shield)](https://app.fossa.io/projects/git%2Bgithub.com%2Fcodingsquare%2FAutonut?ref=badge_shield)

Autonut: Automated donut

## Features
 * /도넛의 개인정보<br>
   도넛의 개인정보를 알아낼 수 있다.

## Todo-list
 * **중복 문자열 전송 제거**
 * 도넛의 개인정보 명령어 하루에 한 번만 사용 가능하게 하기
 * 도전과제 시스템 (ex. 모든 문자열 획득)
 * 사용자 목록 기록 & 업데이트 시 메시지 전송
 * /menu 명령어 추가, 자신이 달성한 도전과제나 얻은 개인정보 목록 확인 등
 * /info 명령어 추가, 도넛봇의 버전이나 기능을 보여주쟈!
 * 통계 시스템


## Getting Started
라이선스에 위배되지 않는다면, 자유롭게 사용하는 것을 허락합니다.
직접 빌드하려면 아래의 가이드를 참고하세요.

### Prerequisites
 * 8 버전 이상의 JDK
 * Gradle CLI

### Installing
다음 명령어를 통해 jar 파일을 빌드하세요.
```
gradlew build
```
### Deployment
jar 파일과 같은 경로에 secrets.txt 라는 파일을 만들어야 실행이 가능합니다.
해당 파일 안에 줄바꿈 문자로 구분된 비밀 정보를 기록해두면 해당 정보가 제공됩니다.
`AUTONUT_TELEGRAM_BOT_TOKEN` 이라는 환경 변수에 텔레그램 봇 토큰을 기입합니다.
그 후 빌드된 jar 파일을 실행하면 텔레그램 봇으로 가동이 시작됩니다.

## Built With
 * Gradle - 의존성 관리 및 빌드 시스템
 * Travis CI - 지속적 통합 서비스
 * fossa.io - 라이선스 충돌 여부 분석

## Contributing
내부 팀원을 제외한 모든 사람의 기여는 무시합니다.

## Versioning
이 프로젝트는 [SemVer 2.0.0](https://semver.org/lang/ko/)을 준수합니다. 존재하는 버전은 태그에서 확인하세요.

## Authors
 * **Ranol** - *프로젝트 총괄* - [RanolP](https://github.com/RanolP)

[기여자 목록](https://github.com/codingsquare/Autonut/contributors)에서 더 많은 기여자를 확인하세요.

## License
이 프로젝트는 GPLv3 라이선스로 관리됩니다. - [라이선스](LICENSE) 파일에서 자세한 사항을 확인하세요.
[![FOSSA Status](https://app.fossa.io/api/projects/git%2Bgithub.com%2Fcodingsquare%2FAutonut.svg?type=large)](https://app.fossa.io/projects/git%2Bgithub.com%2Fcodingsquare%2FAutonut?ref=badge_large)
