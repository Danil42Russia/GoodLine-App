class Activity extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            data: []
        }
    }

    async componentWillMount() {
        const res = await fetch('/ajax/activity');
        const data = await res.json();
        this.setState({data: data});
    }

    render() {
        return (
            <div>
                <table>
                    <thead>
                    <tr>
                        <th>id</th>
                        <th>id_ur</th>
                        <th>ds</th>
                        <th>de</th>
                        <th>vol</th>
                    </tr>
                    </thead>
                    <tbody>
                    {
                        this.state.data.map(item => (
                            <tr key={item.id}>
                                <td>{item.id}</td>
                                <td>{item.id_ur}</td>
                                <td>{item.ds}</td>
                                <td>{item.de}</td>
                                <td>{item.vol}</td>
                            </tr>
                        ))
                    }
                    </tbody>
                </table>
            </div>
        )
    }
}