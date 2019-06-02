class Menu extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            name: "none"
        }
    }

    render() {
        let Child;
        switch (this.state.name) {
            case "user": {
                Child = UserMenu;
                break
            }
            case "authority": {
                Child = Authority;
                break
            }
            case "activity": {
                Child = Activity;
                break
            }
            default: {
                Child = None;
                break
            }
        }

        return (
            <div>
                <div id={"menu"}>
                    <button onClick={() => this.setState({name: "user"})}>User Menu</button>
                    <button onClick={() => this.setState({name: "authority"})}>Authority list</button>
                    <button onClick={() => this.setState({name: "activity"})}>Activity list</button>
                </div>
                <div>
                    <Child/>
                </div>
            </div>
        )
    }
}

ReactDOM.render(
    <Menu/>,
    document.getElementById("root")
);